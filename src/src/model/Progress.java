package model;

public class Progress {
		    public static double calculateProgress(double currentProgress, double targetProgress) {
		        if (targetProgress <= 0) {
		            throw new IllegalArgumentException("目標進捗度は正の数で指定してください。");
		        }
		        double progressPercentage = (currentProgress / targetProgress) * 100;
		        return progressPercentage;
		    }
		    public static void main(String[] args) {
		        double currentProgress = 75.0;
		        double targetProgress = 100.0;
		        double progressPercentage = calculateProgress(currentProgress, targetProgress);
		        System.out.println("進捗度: " + progressPercentage + "%");
		    }
		}















