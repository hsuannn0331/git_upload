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
import java.util.TreeMap;
import java.util.stream.Collectors;

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

			Map<String, List<Map<String, String>>> typesOfManufacturerMap = carMapList.stream().collect(
					Collectors.groupingBy(carMap -> carMap.get("MANUFACTURER"), TreeMap::new, Collectors.toList()));

			System.out.printf("%-14s%-10s%8s%8s\n", "Manufacturer", "TYPE", "Min.PRICE", "Price");
			BigDecimal minTotal = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;
			for (String manufacturer : typesOfManufacturerMap.keySet()) {
				BigDecimal minTotalByType = BigDecimal.ZERO;
				BigDecimal totalByType = BigDecimal.ZERO;
				for (Map<String, String> car : carMapList) {
					BigDecimal minPrice = car.get("MIN.PRICE") != null ? new BigDecimal(car.get("MIN.PRICE"))
							: BigDecimal.ZERO;
					BigDecimal price = car.get("PRICE") != null ? new BigDecimal(car.get("PRICE")) : BigDecimal.ZERO;
					if (car.get("MANUFACTURER").equals(manufacturer)) {
						minTotalByType = minTotalByType.add(minPrice);
						totalByType = totalByType.add(price);

						String minPriceString = (car.get("MIN.PRICE") != null)
								? minPrice.stripTrailingZeros().toPlainString()
								: "";
						String priceString = (car.get("PRICE") != null) ? price.stripTrailingZeros().toPlainString()
								: "";

						System.out.printf("%-14s%-10s%8s%8s\n", car.get("MANUFACTURER"), car.get("TYPE"),
								minPriceString, priceString);
					}
				}
				System.out.printf("%-13s%-10s%8s%8s\n", "小計", "", minTotalByType.stripTrailingZeros().toPlainString(),
						totalByType.stripTrailingZeros().toPlainString());
				minTotal = minTotal.add(minTotalByType);
				total = total.add(totalByType);
			}
			System.out.printf("%-13s%-10s%8s%8s\n", "合計", "", minTotal.stripTrailingZeros().toPlainString(),
					total.stripTrailingZeros().toPlainString());

			Collections.sort(carMapList, new Comparator<Map<String, String>>() {

				public int compare(Map<String, String> o1, Map<String, String> o2) {
					return new BigDecimal(o2.get("PRICE")).compareTo(new BigDecimal(o1.get("PRICE")));
				}

			});
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File("C:/Users/Admin/Desktop/Java班/git_upload/Java/ver3/cars2.csv")),
					StandardCharsets.UTF_8))) {
				bw.write('\ufeff');
				bw.write(headerLine);
				bw.newLine();

				StringBuilder sb = new StringBuilder();
				for (Map<String, String> car : carMapList) {
					BigDecimal minPrice = new BigDecimal(car.get("MIN.PRICE"));
					BigDecimal price = new BigDecimal(car.get("PRICE"));
					sb.append(car.get("MANUFACTURER")).append(",").append(car.get("TYPE")).append(",")
							.append(minPrice.toPlainString()).append(",").append(price.toPlainString());
					bw.write(sb.toString());
					bw.newLine();
					sb.setLength(0);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}