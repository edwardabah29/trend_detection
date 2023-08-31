import pandas as pd

def bullorbear(data, column_name, threshold):
    change_in_trend = []

    for i in range(1, len(data)):
        current_value = data[column_name][i]
        previous_value = data[column_name][i - 1]
        value_change = current_value - previous_value

        if abs(value_change) >= threshold:
            if value_change > 0:
                change_in_trend.append((data.index[i], 'Increasing'))
            else:
                change_in_trend.append((data.index[i], 'Decreasing'))

    return change_in_trend


# Obtain and store the data
data = pd.read_csv('BTC-USD.csv')


# Set the date as index
data = data.set_index(pd.DatetimeIndex(data['Date'].values))

# Set threshold for direction change detection
threshold = 5

# Detect direction changes in the 'Price' column
swings = bullorbear(data, 'Close', threshold)

# Print detected direction changes
for change in swings:
    print(f"Direction at {change[0]}: {change[1]}")

