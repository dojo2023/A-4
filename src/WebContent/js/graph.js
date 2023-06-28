google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawCharts);
function drawCharts() {
var barData = google.visualization.arrayToDataTable([
    ['Day', '運動', '勉強', '読書', 'その他'],
        ['日',  50, 60, 2, 10],
        ['月',  10, 10, 20, 70],
        ['火',  60, 40, 2, 10],
        ['水',  30, 50, 20, 10],
        ['木',  10, 40, 60, 10],
        ['金',  11, 10, 20, 10],
        ['土',  6, 32, 20, 10]
]);
// set bar chart options
var barOptions = {
    focusTarget: 'category', // クリックなどのアクションが発生した場合のフォーカスの対象をカテゴリ（要素）に設定する
    backgroundColor: 'transparent', // グラフの背景色を透明に設定する
    colors: ['#4C3D3D', '#FF8400', '#FFD93D', '#fae9cd'], // 棒グラフの色を指定する（値1の色、値2の色）
    fontName: 'Open Sans', // テキストのフォント名を設定する
    isStacked: true, //積み上げを有効可する
    chartArea: {
        left: 50, // グラフ描画領域の左端の余白を設定する
        top: 10, // グラフ描画領域の上端の余白を設定する
        width: '100%', // グラフ描画領域の幅を100%に設定する
        height: '70%' // グラフ描画領域の高さを70%に設定する
    },
    bar: {
        groupWidth: '60%' // 棒グラフのグループの幅を80%に設定する
    },
    hAxis: {
        textStyle: {
        fontSize: 11 // 横軸（水平軸）のテキストのフォントサイズを11に設定する
        }
    },
    vAxis: {
        minValue: 0, // 縦軸（垂直軸）の最小値を0に設定する
        maxValue: 10, // 縦軸（垂直軸）の最大値を1500に設定する
        baselineColor: '#DDD', // 縦軸のベースライン（基準線）の色を設定する
        gridlines: {
        color: '#DDD', // 縦軸のグリッド線の色を設定する
        count: 10 // 縦軸のグリッド線の数を4に設定する
        },
        textStyle: {
        fontSize: 11 // 縦軸（垂直軸）のテキストのフォントサイズを11に設定する
        }
    },
    legend: {
        position: 'bottom', // 凡例の位置を下部に設定する
        textStyle: {
        fontSize: 12 // 凡例のテキストのフォントサイズを12に設定する
        }
    },
    animation: {
        duration: 1200, // アニメーションの期間を1200ミリ秒に設定する
        easing: 'out', // アニメーションのイージング（進行速度のパターン）を設定する
        startup: true // グラフ描画時にアニメーションを開始する
    }
};

// draw bar chart twice so it animates
var barChart = new google.visualization.ColumnChart(document.getElementById('bar-chart'));
barChart.draw(barData, barOptions);
}
