package com.punch.service.impl;

import static com.punch.entity.Tables.OA_USER;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.jooq.DSLContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.punch.common.entity.LocationProperties;
import com.punch.common.entity.OaProperties;
import com.punch.common.entity.OkHttpRequest;
import com.punch.common.entity.Result;
import com.punch.common.enums.PunchType;
import com.punch.entity.tables.records.OaUserRecord;
import com.punch.model.AddUserDto;
import com.punch.service.IMessageService;
import com.punch.service.IPunchService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiachao
 * @date 2020/07/23 10:16
 */
@Service
@EnableConfigurationProperties(value = LocationProperties.class)
@Slf4j
public class PunchServiceImpl implements IPunchService {

	@Resource
	private LocationProperties locationProperties;

	@Resource
	private OaProperties oaProperties;

	@Resource
	private DSLContext create;

	@Resource
	private IMessageService messageService;

	@Resource
	private AipOcr aipOcr;

	/**
	 * 打卡
	 *
	 * @param loginId loginId
	 * @param pushId  pushId
	 * @param type    类型 上班 or 下班
	 */
	@Override
	@Async
	public void punch(String loginId, String pushId, PunchType type) {
		OaUserRecord user = create.fetchOne(OA_USER, OA_USER.LOGIN_ID.eq(loginId));
		// 用户已停用，停止打卡
		if (user.getEnable() == 0) {
			return;
		}
		int maxPunchNum = 50;
		int punchNum = 0;
		boolean res = tryPunch(loginId, user, maxPunchNum, punchNum, getRandomPoint());
		if (!res) {
			create.update(OA_USER).set(OA_USER.ENABLE, 0).where(OA_USER.LOGIN_ID.eq(loginId)).execute();
			// 失败通知
			messageService.sendMessage(pushId, type.getMemo() + "打卡失败，请手动打卡！");
		} else {
			// 成功通知
			messageService.sendMessage(pushId, type.getMemo() + "打卡成功，打卡时间：" + LocalDateTime.now().toString());
		}
	}

	private LocationProperties.Point getRandomPoint() {
		List<LocationProperties.Point> points = locationProperties.getPoints();
		int size = points.size();
		return points.get(new Random().nextInt(size));
	}

	private boolean tryPunch(String loginId, OaUserRecord user, int maxPunchNum, int punchNum,
			LocationProperties.Point randomPoint) {
		while (punchNum < maxPunchNum) {
			punchNum++;
			String uuid = UUID.randomUUID().toString();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				log.error("sleep", e);
			}
			// 识别验证码
			org.json.JSONObject captcha = aipOcr.basicGeneralUrl(oaProperties.getCaptcha() + "?key=" + uuid, null);
			if (captcha.getInt("words_result_num") == 1) {
				String captchaText = captcha.getJSONArray("words_result").getJSONObject(0).getString("words");
				if (captchaText.length() == 4) {
					AddUserDto dto = new AddUserDto();
					dto.setCaptchaText(captchaText);
					dto.setLoginId(loginId);
					dto.setKey(uuid);
					dto.setPassword(user.getPassword());
					dto.setClientModel(user.getClientModel());
					dto.setDeviceName(user.getDeviceName());
					dto.setDeviceId(user.getDeviceId());
					dto.setOsVersion(user.getOsVersion());
					// 登录
					OkHttpRequest request = new OkHttpRequest(dto);
					Result<JSONObject> result = request.login();
					if (result.isState()) {
						// 授权
						if (request.auth()) {
							// 获取cookies
							if (request.getCookies()) {
								// 打卡
								if (request.punch(randomPoint.getLongitude(), randomPoint.getLatitude())) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

}
