package com.cathaybk.practice.nt50346.b;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JDBC {

	public static final String QUERY_CARS_SQL = "select * from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
	public static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
	public static final String UPDATE_CARS_SQL = "update STUDENT.CARS set MIN_PRICE = ?, PRICE = ? where MANUFACTURER = ? and TYPE = ?";
	public static final String DELETE_CARS_SQL = "delete from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
	public static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";

	public static void main(String[] args) {
		ResultSet rs = null;

		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				PreparedStatement pstmt = conn.prepareStatement("select * from STUDENT.CARS")) {

			rs = pstmt.executeQuery();
			List<Map<String, String>> carMapList = new ArrayList<>();
			while (rs.next()) {
				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				carMap.put("TYPE", rs.getString("TYPE"));
				carMap.put("MIN_PRICE", rs.getString("MIN_PRICE"));
				carMap.put("PRICE", rs.getString("PRICE"));
				carMapList.add(carMap);
			}

			for (Map<String, String> car : carMapList) {
				BigDecimal price = new BigDecimal(car.get("PRICE"));
				BigDecimal minPrice = new BigDecimal(car.get("MIN_PRICE"));

				StringBuilder sb = new StringBuilder();
				sb.append("製造商：").append(car.get("MANUFACTURER")).append("，型號：").append(car.get("TYPE")).append("，底價：$")
						.append(minPrice.toPlainString()).append("，售價：$").append(price.toPlainString());
				System.out.println(sb.toString());
				sb.setLength(0);
			}

			Scanner inputScanner = new Scanner(System.in);
			while (true) {
				System.out.println("請選擇以下指令輸入：select、insert、update、delete");
				String typeOfCommand = inputScanner.nextLine();

				if (typeOfCommand.equals("select")) {
					System.out.print("請輸入製造商： ");
					String manufacturer = inputScanner.nextLine();
					System.out.print("請輸入類型： ");
					String type = inputScanner.nextLine();

					doQuery(conn, manufacturer, type);
				} else if (typeOfCommand.equals("insert")) {
					System.out.print("請輸入製造商： ");
					String manufacturer = inputScanner.nextLine();
					System.out.print("請輸入類型： ");
					String type = inputScanner.nextLine();
					System.out.print("請輸入底價： ");
					BigDecimal minPrice = inputScanner.nextBigDecimal();
					System.out.print("請輸入售價： ");
					BigDecimal price = inputScanner.nextBigDecimal();
					inputScanner.nextLine();

					doInsert(conn, manufacturer, type, minPrice, price);
				} else if (typeOfCommand.equals("update")) {
					System.out.print("請輸入製造商： ");
					String manufacturer = inputScanner.nextLine();
					System.out.print("請輸入類型： ");
					String type = inputScanner.nextLine();
					doQuery(conn, manufacturer, type);
					System.out.print("欲修改底價： ");
					BigDecimal minPrice = inputScanner.nextBigDecimal();
					System.out.print("欲修改售價： ");
					BigDecimal price = inputScanner.nextBigDecimal();
					inputScanner.nextLine();

					doUpdate(conn, minPrice, price, manufacturer, type);
				} else if (typeOfCommand.equals("delete")) {
					System.out.print("請輸入製造商： ");
					String manufacturer = inputScanner.nextLine();
					System.out.print("請輸入類型： ");
					String type = inputScanner.nextLine();

					doDelte(conn, manufacturer, type);
				} else {
					System.out.println("指令錯誤");
					break;
				}
			}
			inputScanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void doQuery(Connection conn, String manufacturer, String type) {
		try (PreparedStatement pstmt = conn.prepareStatement(QUERY_CARS_SQL)) {

			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);

			ResultSet rs = pstmt.executeQuery();

			StringBuilder sb = new StringBuilder();
			sb.append("查詢結果：\n");
			while (rs.next()) {
				sb.append("製造商：").append(rs.getString("MANUFACTURER")).append("，型號：").append(rs.getString("TYPE"))
						.append("，底價：$").append(rs.getString("MIN_PRICE")).append("，售價：$")
						.append(rs.getString("PRICE"));
			}
			System.out.println(sb.toString());
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void doInsert(Connection conn, String manufacturer, String type, BigDecimal minPrice,
			BigDecimal price) {
		try (PreparedStatement pstmt = conn.prepareStatement(INSERT_CARS_SQL)) {

			conn.setAutoCommit(false);

			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);
			pstmt.setBigDecimal(3, minPrice);
			pstmt.setBigDecimal(4, price);
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("新增成功");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				System.out.println("新增失敗");
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void doUpdate(Connection conn, BigDecimal minPrice, BigDecimal price, String manufacturer,
			String type) {
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_CARS_SQL)) {

			conn.setAutoCommit(false);

			pstmt.setBigDecimal(1, minPrice);
			pstmt.setBigDecimal(2, price);
			pstmt.setString(3, manufacturer);
			pstmt.setString(4, type);
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("修改成功");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				System.out.println("修改失敗");
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static void doDelte(Connection conn, String manufacturer, String type) {
		try (PreparedStatement pstmt = conn.prepareStatement(DELETE_CARS_SQL)) {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);
			pstmt.executeUpdate();

			System.out.println("刪除成功");

		} catch (Exception e) {
			System.out.println("刪除失敗");
			e.printStackTrace();
		}
	}
}
