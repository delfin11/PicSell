package kh.picsell.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.picsell.dto.EventDTO;
import kh.picsell.dto.MemberDTO;

@Repository
public class EventDAO {
	
	@Autowired
	private SqlSessionTemplate jdbc;
	
	// 출석체크 
	
		// 1. 그날 출석체크 했는지 안했는지 체크 > 버튼 기능 조절
		public EventDTO todayCheck(String nickname, String event_date) {
			Map<String, String> param = new HashMap<>();
			param.put("nickname", nickname);
			param.put("event_date", event_date);
			return jdbc.selectOne("Event.todayCheck", param);
		}
		
		// 2. 과거의 출석체크 정보 가져오기 
		public List<EventDTO> getEvent(String nickname) {
			return jdbc.selectList("Event.getEvent", nickname);
		}
		
		// 3. 출석체크한 사용자의 닉네임, 날짜 추가
		public int addCalendar(String nickname, String event_date) {
			Map<String, String> param = new HashMap<>();
			param.put("nickname", nickname);
			param.put("event_date", event_date);
			return jdbc.insert("Event.addCalendar", param);
		}
		
		
		/////////////////////////////////////////////////////////////////
		
		// 룰렛 
		public int roulettePoint(String nickname, String point_date, int point) {
			Map<String, Object> param = new HashMap<>();
			param.put("nickname", nickname);
			param.put("point_date", point_date);
			param.put("point", point);
			return jdbc.insert("Event.roulettePoint", param);
			}
		/////////////////////////////////////////////////////////////////
		
		// 정보 수정 시 업데이트 되는 부분 
		
		public void modifyEvent(MemberDTO dto, String nickName) {
			Map<String, Object> map = new HashMap<>();
			map.put("dto", dto);
			map.put("nickName", nickName);
			jdbc.update("Charge.modifyEvent", map);
		}
		
}
