package com.jsp.mcq;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MCQJDBCProject {

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int count = 0;
			int realcount = count + 1;
			System.out.println("Do you want to give this test? Type 'yes' or 'no'");
			String value = sc.next();
			sc.nextLine();
			float answercount = 0;
			if (value.equals("yes")) {
				int wrong_answer;
				for (wrong_answer = 1; wrong_answer <= 20; ++wrong_answer) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/incubation?user=root&password=root");
						Statement st = con.createStatement();
						String query = "select qid,question_no from ques where qid=" + realcount + ";";
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {
							System.out.println("Question: " + rs.getString("qid") + " " + rs.getString("question_no"));
						}

						String Query1 = "select * from opt where fqid=" + realcount + ";";
						ResultSet rs1 = st.executeQuery(Query1);
						System.out.println("Options are: \n");

						while (rs1.next()) {
							System.out.println(rs1.getString("Options_no"));
						}

						System.out.println("Give the Correct Answer for question no " + answercount);
						String answerdo = sc.nextLine();
						String ansquery = "select * from Answer where Aid=" + realcount + ";";
						ResultSet rs2 = st.executeQuery(ansquery);

						while (rs2.next()) {
							if (rs2.getString("Answer").equals(answerdo)) {
								System.out.println("correct answer" + realcount);
								++answercount;
							}
						}

						++realcount;
					} catch (Exception var16) {
						var16.printStackTrace();
					}
				}

				System.out.println("=======================Your Result=========================");
				System.out.println(
						"Correct answers are: " + answercount + " out of 20.0" + " marks is deducted for handwriting");
				wrong_answer = 20 - (int)answercount;
				System.out.println("total wrong answer : " + wrong_answer);
				float percentage = (float) (answercount / 20);
				float final_percentage = percentage * 100.0F;
				System.out.println("Your Correct percentage: " + final_percentage);
				System.out.println("===================Thank you for attending==================");
			} else {
				System.out.println("you do not want to give test.\nThank you. ");
			}

		}
	}

