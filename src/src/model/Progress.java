package model;

public class Progress {
		    public static double calculateProgress(double targetProgress) {
		        if (targetProgress <= 0) {
		            throw new IllegalArgumentException("数字を入れてください");
		        }
		        double progressPercentage = targetProgress * 100;
		        return progressPercentage;
		    }
		    public static void main(String[] args) {

		        double targetProgress = 100.0;
		        double progressPercentage = calculateProgress(targetProgress);
		        System.out.println("進捗度: " + progressPercentage + "%");
		    }
		}















