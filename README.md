**Solution available in python, typescript and java**

![sensitive change detection](https://github.com/edwardabah29/trend_detection/assets/98519652/3d0c0214-0785-450a-9812-7dc7f62b3a0e)

# Sensitive stock price change detection

Python algorithm that detects changes in the direction (increasing or decreasing) of a DataFrame column. The algorithm takes a DataFrame, a column name, and a threshold value as inputs. It then identifies points where the column's values change direction by more than the specified threshold.
In this algorithm, we calculate the difference between consecutive values in the specified column and compare it against the threshold. If the difference is greater than or equal to the threshold, we consider it a change in direction. Positive differences indicate an "Increasing" direction, while negative differences indicate a "Decreasing" direction.
