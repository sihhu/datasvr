/// <reference path="../plugins/jQuery/jQuery-2.1.3.min.js" />
/// <reference path="json.js" />
/// <reference path="baiduTpls.js" />


/*
    本地服务
*/
var hhls = {
    //回调函数
    callBack: function (aCallback, aPara) {
        try {
            if (aCallback) {
                if (aPara) {
                    aCallback(aPara);
                }
                else {
                    aCallback(aPara);
                }
            }
        }
        catch (cer) {
            var m = cer.Message;
        }
    },
    //递归获得本地资源内容
    getLocalRes: function (aResPathList, aCallback) {
        try {
            var aIndex = -1;
            var aResults = [];
            var getRes = function () {
                try {
                    aIndex++;
                    if (aIndex < aResPathList.length) {
                        var aUrl = aResPathList[aIndex];
                        var aItem = { Path: aUrl, Content: "" };
                        $.ajax({
                            url: aUrl,
                            cache: false,
                            success: function (aRes) {
                                aItem.Content = aRes;
                                aResults.push(aItem);
                                getRes();
                            },
                            error: function (a, b, c) {
                                aResults.push(aItem);
                                getRes();
                            }
                        });
                    }
                    else {
                        hhls.callBack(aCallback, aResults);
                    }
                }
                catch (cer) {; }
            }
            getRes();
        }
        catch (cer) {; }
    },
    //清空内容
    clearElement: function (aSelector) {
        try {
            var aSubs = $(aSelector + " *");
            $.each(aSubs, function (aIndex, aItem) {
                $(aItem).remove();
            });
            $(aSelector).empty();
        }
        catch (cer) {; }
    },
    //移除内容
    removeElement: function (aSelector) {
        try {
            if ($(aSelector).length > 0) {
                hhls.clearElement(aSelector);
                $(aSelector).remove();
            }
        }
        catch (cer) {; }
    },
    //填充内容
    fillElement: function (aSelector, aHtml) {
        try {
            hhls.clearElement(aSelector);
            $(aSelector).html(aHtml);
        }
        catch (cer) {; }
    },
    //获得对象
    getObjJson: function (aObj) {
        var aJson = "";
        try {
            aJson = JSON.stringify(aObj);
        }
        catch (e) {
            alert(e);
        }
        return aJson;
    },
    //获得json
    getJsonObj: function (aJson) {
        var aObj = null;
        try {
            aObj = eval('(' + aJson + ')');
        }
        catch (cer) {; }
        return aObj;
    },
    //获取模板
    GetTpls: function (aTpls, aCallback) {
        try {
            var aPs = [];
            var Index = 0;
            for (var p in aTpls) {
                aPs.push(aTpls[p]);
            }
            var aParaPaths = [];
            for (var i = 0; i < aPs.length; i++) {
                aParaPaths.push(aPs[i].P);
            }
            hhls.getLocalRes(aParaPaths, function (aRes) {
                for (var i = 0; i < aRes.length; i++) {
                    aPs[i].C = aRes[i].Content;
                }
                hhls.callBack(aCallback);
            });
        }
        catch (cer) {; }
    },
    //跳转页面
    goUrl: function (aUrl) {
        try {
            var aNewUrl = aUrl;
            aNewUrl += aUrl.indexOf("?") >= 0 ? "&" : "?";
            aNewUrl += "rndurl=" + Math.random();
            window.location.href = aNewUrl;
        }
        catch (cer) { }
    }
};