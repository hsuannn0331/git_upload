package com.cathaybk.practice.nt50346.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CarInfo {

	public static void main(String[] args) {
		try (FileReader fr = new FileReader("C:/Users/Admin/Desktop/Java班/教材/Java/cars.csv");
				BufferedReader br = new BufferedReader(fr)) {
			String headerLine = br.readLine();

			List<Map<String, String>> carMapList = new ArrayList<>();
			String carInfo;
			while ((carInfo = br.readLine()) != null) {
				String[] carData = carInfo.split(",");
				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", carData[0]);
				carMap.put("TYPE", carData[1]);
				carMap.put("MIN.PRICE", carData[2]);
				carMap.put("PRICE", carData[3]);
				carMapList.add(carMap);
			}

			Set<String> typesOfManufacturerSet = new TreeSet<>();
			for (Map<String, String> car : carMapList) {
				typesOfManufacturerSet.add(car.get("MANUFACTURER"));
			}

			System.out.printf("%-14s%-10s%8s%8s\n", "Manufacturer", "TYPE", "Min.PRICE", "Price");
			BigDecimal minTotal = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;
			for (String manufacturer : typesOfManufacturerSet) {
				BigDecimal minTotalByType = BigDecimal.ZERO;
				BigDecimal totalByType = BigDecimal.ZERO;
				for (Map<String, String> car : carMapList) {
					BigDecimal minPrice = new BigDecimal(car.get("MIN.PRICE"));
					BigDecimal price = new BigDecimal(car.get("PRICE"));
					if (car.get("MANUFACTURER").equals(manufacturer)) {
						minTotalByType = minTotalByType.add(minPrice);
						totalByType = totalByType.add(price);
						System.out.printf("%-14s%-10s%8s%8s\n", car.get("MANUFACTURER"), car.get("TYPE"),
								minPrice.stripTrailingZeros().toPlainString(),
								price.stripTrailingZeros().toPlainString());
					}
				}
				System.out.printf("%-14s%-10s%8s%8s\n", "小計", "", minTotalByType.stripTrailingZeros().toPlainString(),
						totalByType.stripTrailingZeros().toPlainString());
				minTotal = minTotal.add(minTotalByType);
				total = total.add(totalByType);
			}
			System.out.printf("%-14s%-10s%8s%8s\n", "合計", "", minTotal.stripTrailingZeros().toPlainString(),
					total.stripTrailingZeros().toPlainString());

			Collections.sort(carMapList, new Comparator<Map<String, String>>() {

				public int compare(Map<String, String> o1, Map<String, String> o2) {
					BigDecimal price1 = new BigDecimal(o1.get("PRICE"));
					BigDecimal price2 = new BigDecimal(o2.get("PRICE"));
					return price2.compareTo(price1);
				}

			});
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File("C:/Users/Admin/Desktop/Java班/教材/Java/cars2.csv")),
					StandardCharsets.UTF_8))) {
				bw.write('\ufeff');
				bw.write(headerLine);
				bw.newLine();

				for (Map<String, String> car : carMapList) {
					BigDecimal minPrice = new BigDecimal(car.get("MIN.PRICE"));
					BigDecimal price = new BigDecimal(car.get("PRICE"));
					bw.write(car.get("MANUFACTURER") + "," + car.get("TYPE") + "," + minPrice.toPlainString() + ","
							+ price.toPlainString());
					bw.newLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}