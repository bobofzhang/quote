Highcharts.seriesTypes.candlestick.prototype.getAttribs = function() {
  Highcharts.seriesTypes.column.prototype.getAttribs.apply(this, arguments);
  var series = this, options = series.options, stateOptions = options.states, upColor = options.upColor || series.color, seriesDownPointAttr = Highcharts
      .merge(series.pointAttr), upColorProp = series.upColorProp;
  seriesDownPointAttr['']['stroke'] = upColor;
  seriesDownPointAttr[''][upColorProp] = upColor;
  seriesDownPointAttr.hover[upColorProp] = stateOptions.hover.upColor || upColor;
  seriesDownPointAttr.select[upColorProp] = stateOptions.select.upColor || upColor;
  Highcharts.each(series.points, function(point) {
    if (point.open < point.close) {
      point.pointAttr = seriesDownPointAttr;
    }
  });
};

Highcharts.theme = {
  global : {
    useUTC : false
  },
  lang : {
    loading : '载入中...',
    months : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],
    shortMonths : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ],
    weekdays : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
    decimalPoint : '.',
    numericSymbols : [],

    rangeSelectorFrom : '从',
    rangeSelectorTo : '到',
    rangeSelectorZoom : '缩放',
    resetZoom : '重置缩放',
    resetZoomTitle : '原始比例',
    thousandsSep : ','
  },

  xAxis : {

    dateTimeLabelFormats : {
      millisecond : '%H:%M:%S.%L',
      second : '%H:%M:%S',
      minute : '%H:%M',
      hour : '%H:%M',
      day : '%b%e日',
      week : '%b%e日',
      month : '%y年%b',
      year : '%Y'
    }
  },

  credits : {
    enabled : false
  },

  plotOptions : {
    series : {
      shadow : true,
      animation : false,
      valueDecimals : 4,
      dataGrouping : {
          enabled : false
      }
    },   
    candlestick : {
      color : '#090',
      lineColor : '#090',
      upColor : '#c00',
      tooltip : {
        xDateFormat : '%Y/%m/%d %a',
        pointFormat : '<span style="color:{series.color};font-weight:bold">{series.name}</span><br/>'
            + '开盘: {point.open}<br/>' + '最高: {point.high}<br/>' + '最低: {point.low}<br/>' + '收盘: {point.close}<br/>'
      },
      states : {
          enabled : false,
          hover : {
              lineColor : '#00c',
              lineWidth : 1
          }
      }

    }
  },

  rangeSelector : {
    enabled : false,
    buttons : [ {
      type : 'month',
      count : 1,
      text : '一月'
    }, {
      type : 'month',
      count : 3,
      text : '三月'
    }, {
      type : 'month',
      count : 6,
      text : '半年'
    }, {
      type : 'ytd',
      text : '今年'
    }, {
      type : 'year',
      count : 1,
      text : '一年'
    }, {
      type : 'all',
      text : '全部'
    } ]

  }

};

// Apply the theme
var highchartsOptions = Highcharts.setOptions(Highcharts.theme);
