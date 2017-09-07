/// <reference path="../../Libs/sdk/jQuery-2.1.3.min.js" />
/// <reference path="../../Libs/sdk/json.js" />
/// <reference path="../../Libs/sdk/baiduTpls.js" />
/// <reference path="../../Libs/sdk/date.js" />
/// <reference path="../../Libs/sdk/hhls.js" />
/// <reference path="../../Libs/sdk/hhac.js" />

//初始化系统 
function doInitSys() {
    Ac.Info.SvcUrl = "http://127.0.0.1:8080/dv/getData";
}

var Init = {
    //数据
    Datas: {
        TomcatUrl: "http://127.0.0.1:8080/",
    },
    //sql语句的路径
    Path: {
        Home_GetHouse: "Sqls/HRS/Manage/Home/GetHouse.txt",
        Home_GetCollectState: "Sqls/HRS/Manage/Home/GetCollectState.txt",
        Home_doCollect: "Sqls/HRS/Manage/Home/doCollect.txt", 
        Home_GetRent: "Sqls/HRS/Manage/Home/GetRent.txt",
        Home_GetType: "Sqls/HRS/Manage/Home/GetType.txt",
        Home_GetDecorate: "Sqls/HRS/Manage/Home/GetDecorate.txt",
        Home_GetRentMode: "Sqls/HRS/Manage/Home/GetRentMode.txt",
        Zone_GetUserInfo: "Sqls/HRS/Manage/Zone/GetUserInfo.txt",
        Zone_EditUserInfo: "Sqls/HRS/Manage/Zone/EditUserInfo.txt",
        Zone_EditPwd: "Sqls/HRS/Manage/Zone/EditPwd.txt",
        Zone_UpdateHouseState: "Sqls/HRS/Manage/Zone/UpdateHouseState.txt",
        Zone_GetUpdateHouse: "Sqls/HRS/Manage/Zone/GetUpdateHouse.txt",
        Zone_GetCollectHouse: "Sqls/HRS/Manage/Zone/GetCollectHouse.txt",
        Zone_DeleteCollect: "Sqls/HRS/Manage/Zone/DeleteCollect.txt",
        Sys_GetUsers: "Sqls/HRS/Manage/Sys/GetUsers.txt",
        Sys_GetRent: "Sqls/HRS/Manage/Sys/GetRent.txt",
    },
    //web弹出框样式
    Utility: { 
        WebToast: "<div id=\"webToast\">"
                    + "<div class=\"web_transparent\"></div>"
                    + "<div class=\"web-toast\">"
                        + "<div class=\"sk-spinner sk-spinner-three-bounce\">"
                            + "<div class=\"sk-bounce1\"></div>"
                            + "<div class=\"sk-bounce2\"></div>"
                            + "<div class=\"sk-bounce3\"></div>"
                        + "</div>"
                        + "<p class=\"web-toast_content\">数据加载中</p>"
                    + "</div>"
                + "</div>",
    },
    //web弹出框
    WebToast: function (aInfo) {
        var me = Init;
        try {
            $("body").append(me.Utility.WebToast);
            var w = $(window).width();
            var aW = $(".web-toast").width();
            var left = (w - aW) / 2;
            $(".web-toast").css("left", left + "px");
            if(aInfo != "")
            $(".web-toast_content").text(aInfo);
        }
        catch (e) {; }
    },
    //清除弹出框,设置时间
    ClearToast: function (aElement, aTimeOut) {
        var me = Init;
        try { 
            setTimeout(function() {
                $(aElement).remove();
            }, aTimeOut * 1000);
        }
        catch (e) {; }
    },
    //加载图片
    LoadWxImg: function () {
        var me = Init;
        try {
            var aImgs = $(".WxImg");
            $.each(aImgs, function (aInd, aItem) {
                try {
                    var aImg = $(aItem);
                    var aKey = aImg.attr("Key");
                    if (aKey.length > 0) {
                        var aUrl = me.Datas.TomcatUrl + aKey;
                        aImg.attr("src", aUrl);
                    }
                }
                catch (ee) {; }
            });
        }
        catch (e) {; }
    }

}