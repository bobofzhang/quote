$.extend($.fn.fmatter, {
  bar : function(cellvalue, options, rowdata) {
    var op = $.extend({}, options.bar);
    if (options.colModel !== undefined && options.colModel.formatoptions !== undefined) {
      op = $.extend({}, op, options.colModel.formatoptions);
    }
    var percentField = op.percentField || "percent";
    var barColor = op.barColor || "red";
    var textColor = op.textColor || "black";
    var p = (rowdata[percentField] * 100).toFixed(2) + "%";
    return "<div style=\"position:relative;left:0px;top:0px;z-index:1;width:100%;height:16px;\" >"
        + "<span style=\"position:absolute;left:0px;top:0px;z-index:3;background-color:" + barColor + ";width:" + p
        + ";\"> </span><span style=\"position:absolute;left:0px;top 0px;z-index:4;width:100%;text-align:right;color:"+textColor+"\">"
        + cellvalue + "</span></div>";
  },

  change : function(cellvalue, options, rowdata) {
    var op = $.extend({}, options.change);
    if (options.colModel !== undefined && options.colModel.formatoptions !== undefined) {
      op = $.extend({}, op, options.colModel.formatoptions);
    }
    var changeField = op.changeField || "change";
    var color = op.color || "black";
    var upColor = op.upColor || "red";
    var downColor = op.downColor || "green";

    var c = color;
    var change = rowdata[changeField];
    if (change > 0) {
      c = upColor;
    } else if (change < 0) {
      c = downColor;
    }
    return "<span style=\"color:" + c + "\">" + cellvalue + "</span>";
  },
  
  arrow : function (cellvalue, options, rowdata) {
    var op = $.extend({}, options.arrow);
    if (options.colModel !== undefined && options.colModel.formatoptions !== undefined) {
      op = $.extend({}, op, options.colModel.formatoptions);
    }
    var changeField = op.changeField || "change";
    var color = op.color || "black";
    var upColor = op.upColor || "red";
    var downColor = op.downColor || "green";
    
    var arrowField = op.arrowField || "arrow";
    var arrowCls = op.arrowCls || "ui-icon ui-icon-arrowthick-1-e";
    var upArrowCls = op.upArrowCls || "ui-icon ui-icon-arrowthick-1-n";
    var downArrowCls = op.downArrowCls || "ui-icon ui-icon-arrowthick-1-s";
    
    var c = color;
    var change = rowdata[changeField];
    if (change > 0) {
      c = upColor;
    } else if (change < 0) {
      c = downColor;
    }
    
    var cls = arrowCls;
    var arrow = rowdata[arrowField];
    if (arrow > 0) {
      cls = upArrowCls;
    } else if (arrow < 0) {
      cls = downArrowCls;
    }
    
    
    return "<span style=\"color:" + c + "\">" + cellvalue
        + "<i class=\""+cls+"\" style=\"display:inline-block;\" ></i></span>";
  }

});