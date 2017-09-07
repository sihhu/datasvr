/// <reference path="../plugins/jQuery/jQuery-2.1.3.min.js" />
/// <reference path="json.js" />
/// <reference path="baiduTpls.js" />


/*
    后台对接服务
*/
var Ac = {
    Info: {
        SvcUrl: "", 
    },
    Call_Post: function (aAction, aPs, aCallback) {
        try {
            var aUrl = Ac.Info.SvcUrl; 
            aUrl += aUrl.indexOf("?") > 0 ? "&" : "?";
            aUrl += "Action=" + aAction; 
            aUrl += "&rnd=" + Math.random();
            $.ajax({
                url: aUrl,
                data: aPs,
                type: 'POST',
                timeout: 50000,
                success: function (aCallbackData, b, c) {
                    var aResult = hhls.getJsonObj(aCallbackData);
                    hhls.callBack(aCallback, aResult);
                },
                error: function (a, b, c) {
                    var aResult = { State: 0, Datas: { Ea: a, Eb: b, Ec: c} };
                    hhls.callBack(aCallback, aResult);
                }
            });
        }
        catch (E) { ; }
    },
    //获得单个表数据
    acGetTable: function (aPath, aDataPs, aCallback) {
        try {
            var aPostPs = {
                Path: aPath,
                Ps: hhls.getObjJson(aDataPs)
            };
            Ac.Call_Post("acGetTable", aPostPs, function (aRes) {
                var aResult = {
                    State: aRes.State,
                    DebugInfos: aRes.DebugInfos,
                    Datas: aRes.Datas
                };
                if (aResult.Datas == null) {
                    aResult.Datas = [];
                }
                hhls.callBack(aCallback, aResult);
            });
        }
        catch (E) {
            alert(E);
        }
    },
    //获得分页数据
    acGetPageTable: function (aPath, aOrderFields, aPageSize, aPageIndex, aDataPs, aCallback) {
        try {
            var aPostPs = {
                Path: aPath,
                Ps: hhls.getObjJson(aDataPs),
                OrderFields: aOrderFields,
                PageSize: aPageSize,
                PageIndex: aPageIndex
            };
            Ac.Call_Post("acGetPageTable", aPostPs, function (aRes) {
                aRes.Datas.DataList = hhls.getJsonObj(aRes.Datas.Datajson);
                var aResult = {
                    State: aRes.State,
                    DebugInfos: aRes.DebugInfos,
                    Datas: aRes.Datas
                };
                if (aRes.Datas.DataList == null) {
                    aRes.Datas.DataList = [];
                }
                hhls.callBack(aCallback, aResult);
            });
        }
        catch (E) {
            alert(E);
        }
    },
    //获得多个表数据
    acGetDs: function (aTables, aDataPs, aCallback) {
        try {
            var aPostPs = {
                Tables: hhls.getObjJson(aTables),
                Ps: hhls.getObjJson(aDataPs)
            };
            Ac.Call_Post("acGetDs", aPostPs, function (aRes) {
                var aResult = {
                    State: aRes.State,
                    DebugInfos: aRes.DebugInfos,
                    Datas: aRes.Datas
                };
                hhls.callBack(aCallback, aResult);
            });
        }
        catch (E) {
            alert(E);
        }
    },
    //执行sql操作
    acExecuteSql: function (aPath, aDataPs, aCallback) {
        try {
            var aPostPs = {
                Path: aPath,
                Ps: hhls.getObjJson(aDataPs)
            };
            Ac.Call_Post("acExecuteSql", aPostPs, function (aRes) {
                hhls.callBack(aCallback, aRes);
            });
        }
        catch (E) {
            alert(E);
        }
    },  
};