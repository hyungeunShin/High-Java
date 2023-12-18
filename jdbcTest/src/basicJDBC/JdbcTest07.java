package basicJDBC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTest07 {
	public static void main(String[] args) {
        try {
        	List<Map<String,Object>> dbDataList = readDB();
        	List<Map<String,Object>> csvDataList = readCSV("C:/testA/test1.csv");
			
        	if(!dbDataList.isEmpty() && !csvDataList.isEmpty()) {
        		for(int i = 0; i < csvDataList.size(); i++) {
        			
        			boolean flag = false;
        			
        			for (int j = 0; j < dbDataList.size(); j++) {
        				if (csvDataList.get(i).get("code").equals(dbDataList.get(j).get("code")) && csvDataList.get(i).get("gubn").equals(dbDataList.get(j).get("gubn"))) {
        					dbDataList.remove(j);
        					dbDataList.add(csvDataList.get(i));
        					flag = true;
        					break;
        				} 
        			}
        			
        			if(!flag) {
        				dbDataList.add(csvDataList.get(i));
        			}
        		}
        		
        		//DB Insert
                //int insert = insert(dbDataList);
                //System.out.println(insert + "개의 행이 등록됨");
                
                //파일 삭제
    			//String deleteFile = deleteFile("C:/testA/test1.csv");
    			//System.out.println(deleteFile);
                
                //.csv파일
                //wrtieFile("C:/testA", getNowFileName(), dbDataList);
        	} 
        } catch(Exception e) {
            e.printStackTrace();
        } 
	}
	
	public static List<Map<String, Object>> readDB() throws Exception {
		Class.forName("org.postgresql.Driver");
		
		String url  	= "";
        String user     = "";
        String password = "";
		String query 	= "SELECT * FROM public.pas_code";
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		try(Connection con = DriverManager.getConnection(url, user, password);
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);) {
			
    		ResultSetMetaData metaData = rs.getMetaData();
    		int columnCount = metaData.getColumnCount();

    		while (rs.next()) {
    			Map<String, Object> row = new HashMap<>();
    			for (int i = 1; i <= columnCount; i++) {
    				String columnName = metaData.getColumnName(i);
    				Object value = rs.getObject(i);
    				row.put(columnName, value);
    			}

    			// 리스트에 추가
    			resultList.add(row);
    		}
		}
		
		return resultList;
	}
	
	public static int insert(List<Map<String, Object>> list) throws Exception {
		Class.forName("org.postgresql.Driver");
		
		String url  	= "";
        String user     = "";
        String password = "";
		String query 	= "insert into ojt.test_c("
				+ "user_cd_div,\r\n"
				+ "	user_cd,\r\n"
				+ "	user_cd_attrb,\r\n"
				+ "	manage_item_cd_n1,\r\n"
				+ "	manage_item_dtls_n1,\r\n"
				+ "	manage_item_cd_attrb_n1,\r\n"
				+ "	manage_item_cd_n2,\r\n"
				+ "	manage_item_dtls_n2,\r\n"
				+ "	manage_item_cd_attrb_n2,\r\n"
				+ "	manage_item_cd_n3,\r\n"
				+ "	manage_item_dtls_n3,\r\n"
				+ "	manage_item_cd_attrb_n3,\r\n"
				+ "	manage_item_cd_n4,\r\n"
				+ "	manage_item_dtls_n4,\r\n"
				+ "	manage_item_cd_attrb_n4,\r\n"
				+ "	manage_item_cd_n5,\r\n"
				+ "	manage_item_dtls_n5,\r\n"
				+ "	manage_item_cd_attrb_n5,\r\n"
				+ "	std_cd_nm,\r\n"
				+ "	std_cd_nm_abrv,\r\n"
				+ "	std_cd_nm_eng,\r\n"
				+ "	user_cd_div_nm,\r\n"
				+ "	std_cd_lt,\r\n"
				+ "	upd_imprty_yn\r\n"
				+ "	) values("
				+ " ?,?,?,?,?,?,"
				+ " ?,?,?,?,?,?,"
				+ " ?,?,?,?,?,?,"
				+ " ?,?,?,?,?,?)";
		
		try(Connection con = DriverManager.getConnection(url, user, password);
	        PreparedStatement stmt = con.prepareStatement(query);) {
			
			int result = 0;
			
			for(int i = 0; i < list.size(); i++) {
				result++;
				
				Map<String, Object> map = list.get(i);
				
				stmt.setString(1,  (String) map.get("gubn"));	stmt.setString(2,  (String) map.get("code"));	stmt.setString(3,  (String) map.get("attr"));
				stmt.setString(4,  (String) map.get("int1"));	stmt.setString(5,  (String) map.get("dsc1"));	stmt.setString(6,  (String) map.get("atr1"));
				stmt.setString(7,  (String) map.get("int2"));	stmt.setString(8,  (String) map.get("dsc2"));	stmt.setString(9,  (String) map.get("atr2"));
				stmt.setString(10, (String) map.get("int3"));	stmt.setString(11, (String) map.get("dsc3"));	stmt.setString(12, (String) map.get("atr3"));
				
				stmt.setString(13, (String) map.get("int4"));	stmt.setString(14, (String) map.get("dsc4"));	stmt.setString(15, (String) map.get("atr4"));
				stmt.setString(16, (String) map.get("int5"));	stmt.setString(17, (String) map.get("dsc5"));	stmt.setString(18, (String) map.get("atr5"));
				stmt.setString(19, (String) map.get("cnam"));	stmt.setString(20, (String) map.get("cabr"));	stmt.setString(21, (String) map.get("cnme"));
				stmt.setString(22, (String) map.get("gbnm"));
				
				String clthValue = String.valueOf(map.get("clth"));
				Integer clth = "null".equals(clthValue) ? null : Integer.valueOf(clthValue);

				if (clth == null) {
				    stmt.setNull(23, Types.NUMERIC);
				} else {
				    stmt.setInt(23, clth);
				}
				
				stmt.setString(24, (String) map.get("ncyb"));
				
				stmt.addBatch();
				stmt.clearParameters();
				
				//addBatch할 때 너무 많은 쿼리문을 메모리에 올리면
				//OutOfMemory 발생
				if((i % 100) == 0) {
					stmt.executeBatch();
					stmt.clearBatch();
				}
			}
			
			return result;
		} 
	}
	
	public static List<Map<String, Object>> readCSV(String fileName) throws Exception {
		try(BufferedReader br = new BufferedReader(new FileReader(fileName));) {
			String str;
			List<String[]> strList = new ArrayList<>();
			while ((str = br.readLine()) != null) {
				String[] strArr = str.replaceAll("\"", "").split(",");
				strList.add(strArr);
			}
			
			List<String> csvKeys = new ArrayList<String>();
			List<Map<String, Object>> csvList = new ArrayList<Map<String, Object>>();

			for (int i = 0; i < strList.size(); i++) {
				Map<String, Object> csvMap = new HashMap<String, Object>();
				String[] csvStrArr = strList.get(i);

				for (int j = 0; j < csvStrArr.length; j++) {
					if (i == 0) {
						csvKeys.add(csvStrArr[j]);
					} else {
						String val = csvStrArr[j];
						if ("".equals(val)) {
							val = null;
						}
						csvMap.put(csvKeys.get(j).toString(), val);
					}
				}
				
				if (i != 0) {
					csvList.add(csvMap);
				}
			}
			return csvList;
		}
	}
	
	public static String deleteFile(String fileName) {
		File file = new File(fileName);
		
		if(file.exists()) {
    		if(file.delete()) {
    			return "파일삭제 성공";
    		}else{
    			return "파일삭제 실패";
    		}
    	} else {
    		return "파일이 존재하지 않습니다.";
    	}
	}
	
	public static void wrtieFile(String dir, String fileName) throws Exception {
		Class.forName("org.postgresql.Driver");
		
		String url  	= "";
        String user     = "";
        String password = "";
		String query 	= "SELECT * FROM ojt.test_c";
		
		File directory = new File(dir);
		File file = new File(dir + File.separator + fileName);
		
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		try(Connection con = DriverManager.getConnection(url, user, password);
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
			
    		ResultSetMetaData metaData = rs.getMetaData();
    		int columnCount = metaData.getColumnCount();
    		
    		String strKey = "";
			String strVal = "";
			
    		for(int i = 1; i <= columnCount; i++) {
    			if(i == 1) {
    				strKey += "\"" + metaData.getColumnName(i) + "\"";
    			} else {
    				strKey += ",\"" + metaData.getColumnName(i) + "\"";
    			}
    		}
    		bw.write(strKey);
    		bw.newLine();
    		
    		while (rs.next()) {
    			strVal = "";
    			for (int i = 1; i <= columnCount; i++) {
    				Object value = rs.getObject(i);
    				if(i == 1) {
    					strVal += value;
    				} else {
    					strVal += "," + value;
    				}
    			}
    			bw.write(strVal);
    			bw.newLine();
    		}
    		bw.flush();
		}
	}
	
	public static String getNowFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		Date now = new Date();         
		String nowTime = sdf.format(now);
		return "test_" + nowTime + ".csv";
	}
} 
