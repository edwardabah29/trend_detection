import * as fs from 'fs';

interface DataPoint {
    Date: string;
    Close: number;
}

function bullOrBear(data: DataPoint[], threshold: number): [string, number][] {
    const changeInTrend: [string, number][] = [];

    for (let i = 1; i < data.length; i++) {
        const currentValue = data[i].Close;
        const previousValue = data[i - 1].Close;
        const valueChange = currentValue - previousValue;

        if (Math.abs(valueChange) >= threshold) {
            if (valueChange > 0) {
                changeInTrend.push(['Increasing', data[i].Close]);
            } else {
                changeInTrend.push(['Decreasing', data[i].Close]);
            }
        }
    }

    return changeInTrend;
}

// Read data from CSV file and populate the 'data' array
const rawData: string = fs.readFileSync('BTC-USD.csv', 'utf-8');
const rows: string[] = rawData.trim().split('\n').slice(1);
const data: DataPoint[] = rows.map((row) => {
    const [Date, Close] = row.split(',');
    return { Date, Close: parseFloat(Close) };
});

const threshold: number = 5;
const swings: [string, number][] = bullOrBear(data, threshold);

// Print detected direction changes
swings.forEach((change) => {
    console.log(`Direction: ${change[0]}, Value: ${change[1]}`);
});
