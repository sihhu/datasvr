/// <reference path="../../../../../Libs/sdk/jQuery-2.1.3.min.js" />
/// <reference path="../../../../../Libs/sdk/hhac.js" />
/// <reference path="../../../../../Libs/sdk/hhls.js" />
/// <reference path="../../../../../Libs/sdk/hhls_wxConfirm.js" />
/// <reference path="../../../../../Libs/sdk/hhsWx.js" />
/// <reference path="../../../../../Libs/sdk/hhwxMsg.js" />
/// <reference path="../../../../../Libs/sdk/hhwxVoiceContent.js" />
/// <reference path="../../../../../Libs/sdk/baiduTpls.js" /> 
/// <reference path="../../../Commons/Init.js" /> 
/// <reference path="../../../Commons/area.js" />


var Home = {
    //基本参数
    Datas: {
        //房屋列表信息（分页操作）
        Houses: {
            OrderFields: "time desc",
            PageSize: 5,
            PageIndex: 1,
            PageCount: [], //页数
            RowCount: [],//总数
            DataList: []
        },
        //字典搜索条件 Rent、Type、Decorate、RentMode（动态获取）
        Rent: [
           { Caption: "不限" }, 
        ],
        Type: [
           { Caption: "不限" }, 
        ],
        Decorate: [
            { Caption: "不限" }, 
        ],
        RentMode: [
            { Caption: "不限" }, 
        ],
        //选择的筛选条件，0、1、2、3   共数组的4条
        Condition: [],
        //选择的那一条租房信息
        HouseDetail: null,
        //选择参数信息，sql语句的替换参数
        ChooseCaption: {
            TID: 0,
            RID: 0,
            DID: 0,
            RMID: 0,
            province: 0,
            city: 0,
            district: 0,
            Hvillage: "",
        }
    },
    //模板   P: 路径   C: 内容
    Tpls: {
        tplPage: { P: "Modules/Home/tplPage.html", C: "" },
        tplList: { P: "Modules/Home/tplList.html", C: "" },
        tplHouseDetail: { P: "Modules/Home/tplHouseDetail.html", C: "" },
    },
    //加载模板及其他内容
    Load: function () {
        var me = Home;
        try {
            Init.WebToast("数据加载中");
            hhls.GetTpls(me.Tpls, function () {
                me.getDicts(function () {
                    var aHtml = me.Tpls.tplPage.C;
                    hhls.fillElement("#divBody", aHtml);
                    //固定右边详细信息 
                    var aRight = $(".Houses .container .row .col-md-9").offset().left;
                    $(".Houses .container .row .col-md-3").css("right", aRight + "px");

                    var aW = $(".Houses .container .row .col-md-9").width() / 3;
                    $(".Houses .container .row .col-md-3").css("width", aW + "px");
                    me.InitCondition();
                    //初始化加载地理位置信息，级联样式
                    _init_area();
                    area.RefreshFlag = true;
                    me.Refresh();
                });
            });
        } catch (e) {; }
    },
    //刷新页面
    Refresh: function () {
        var me = Home;
        try {
            //检查搜索框中是否需要有填写
            me.Datas.ChooseCaption.Hvillage = $("#txtQueryText").val() == "" ? "" : $("#txtQueryText").val();
            //分页查询
            Ac.acGetPageTable(Init.Path.Home_GetHouse, me.Datas.Houses.OrderFields, me.Datas.Houses.PageSize, me.Datas.Houses.PageIndex, me.Datas.ChooseCaption, function (aRes) {
                $("#webToast").remove();
                me.Datas.Houses = aRes.Datas;
                if (aRes.State == 1) {
                    var aHtml = bt(me.Tpls.tplList.C, { tplData: me.Datas.Houses });
                    hhls.fillElement(".HouseList", aHtml);
                    if (me.Datas.Houses.DataList.length)
                        Init.LoadWxImg();
                }
            });
        } catch (e) {; }
    },
    //获得字典参数，多个数据Table集
    getDicts: function (aCallBack) {
        var me = Home;
        try {
            var aTables = {
                Rent: Init.Path.Home_GetRent,
                Type: Init.Path.Home_GetType,
                Decorate: Init.Path.Home_GetDecorate,
                RentMode: Init.Path.Home_GetRentMode,
            };
            Ac.acGetDs(aTables, {}, function (aRes) {
                //将不限后台获取的数据合并
                me.Datas.Decorate = aRes.Datas.Decorate;
                me.Datas.Type = aRes.Datas.Type;
                me.Datas.RentMode = aRes.Datas.RentMode;
                me.Datas.Rent = aRes.Datas.Rent;
                hhls.callBack(aCallBack);
            });
        } catch (e) {; }
    },
    //初始化显示条件信息，一维数组
    InitCondition: function () {
        var me = Home;
        try {
            me.AppendData(".divRent table tr", 0, me.Datas.Rent);
            me.AppendData(".divType table tr", 1, me.Datas.Type);
            me.AppendData(".divDecorate table tr", 2, me.Datas.Decorate);
            me.AppendData(".divRentMode table tr", 3, me.Datas.RentMode);
        } catch (e) {; }
    },
    //添加具体内容
    AppendData: function (aElement, aKind, aInfo) {
        var aStr = "";
        var aOnclick = "";
        for (var i in aInfo) {
            aOnclick = "onclick='Home.PickItem(" + i + ", " + aKind + ")'";
            aStr += "<td class='editor' " + aOnclick + ">" + aInfo[i].Caption + "</td>";
        }
        $(aElement).append(aStr);
        var aMenu = $(aElement + " .editor");
        aMenu.removeClass("active");
        $(aMenu[0]).addClass("active");
    },
    //选择筛选条件
    PickItem: function (aIndex, aKind) {
        var me = Home;
        try {
            var aMenu = "";
            var aInfo = {};
            if (aKind == 0) {
                aMenu = $(".divRent table tr .editor");
                aInfo.C = aIndex == 0 ? "" : me.Datas.Rent[aIndex].Caption;
                aInfo.P = "Rent";
                me.Datas.ChooseCaption.RID = aIndex;
            } else if (aKind == 1) {
                aMenu = $(".divType table tr .editor");
                aInfo.C = aIndex == 0 ? "" : me.Datas.Type[aIndex].Caption;
                aInfo.P = "Type";
                me.Datas.ChooseCaption.TID = aIndex;
            } else if (aKind == 2) {
                aMenu = $(".divDecorate table tr .editor");
                aInfo.C = aIndex == 0 ? "" : me.Datas.Decorate[aIndex].Caption;
                aInfo.P = "Decorate";
                me.Datas.ChooseCaption.DID = aIndex;
            } else if (aKind == 3) {
                aMenu = $(".divRentMode table tr .editor");
                aInfo.C = aIndex == 0 ? "" : me.Datas.RentMode[aIndex].Caption;
                aInfo.P = "RentMode";
                me.Datas.ChooseCaption.RMID = aIndex;
            }
            aMenu.removeClass("active");
            $(aMenu[aIndex]).addClass("active");

            me.Datas.Condition[aKind] = aInfo;
            me.ReplaceData();
        } catch (e) {; }
    },
    //在筛选条件后面显示
    ReplaceData: function () {
        var me = Home;
        try {
            var aStr = "";
            var aOnclick = "";
            for (var i in me.Datas.Condition) {
                if (me.Datas.Condition[i].C != "") {
                    aOnclick = "Home.ClearItem(" + i + ",'" + me.Datas.Condition[i].P + "')";
                    aStr += "<td class='searchBtn " + i + "'><button class='btn btn-xs btn-warning' onclick=" + aOnclick + ">" + me.Datas.Condition[i].C + " <i class='fa fa-close text-danger'></i></button></td>";
                }
            }
            $(".searchBtn").remove();
            if (aStr != "") {
                $("#lab").css("display", "none");
                $(".divSearch table tr").append(aStr);
            } else {
                $("#lab").css("display", "");
            }
            Init.WebToast();
            me.Refresh();
        } catch (e) {; }
    },
    //筛选条件中的清除
    ClearItem: function (aIndex, aStr) {
        var me = Home;
        try {
            Init.WebToast();
            $(".divSearch table tr td." + aIndex).remove();
            var aElement = ".div" + aStr + " table tr .editor"
            var aMenu = $(aElement);
            aMenu.removeClass("active");
            $(aMenu[0]).addClass("active");

            if (aStr == "Rent") {
                me.Datas.ChooseCaption.RID = 0;
                me.Datas.Condition[0].C = "";
            }
            else if (aStr == "Type") {
                me.Datas.ChooseCaption.TID = 0;
                me.Datas.Condition[1].C = "";
            }
            else if (aStr == "Decorate") {
                me.Datas.ChooseCaption.DID = 0;
                me.Datas.Condition[2].C = "";
            }
            else if (aStr == "RentMode") {
                me.Datas.ChooseCaption.RMID = 0;
                me.Datas.Condition[3].C = "";
            }
            if (me.Datas.ChooseCaption.RID == 0 && me.Datas.ChooseCaption.TID == 0 && me.Datas.ChooseCaption.DID == 0 && me.Datas.ChooseCaption.RMID == 0) {
                $("#lab").css("display", "");
            }
            me.Refresh();

        } catch (e) {; }
    }, 
    //根据Action的数字判断更改页码
    ChangePage: function (aAction) {
        var me = Home;
        try {
            var flag = false;
            Init.WebToast();
            if (aAction == 0) {
                me.Datas.Houses.PageSize = parseInt($(".cmbPageSize").val());
            }
            else if (aAction == 1) {
                if (me.Datas.Houses.PageIndex > 1)
                    me.Datas.Houses.PageIndex = 1;
                else {
                    flag = true;
                    $(".web-toast_content").text("当前已经是首页");
                    Init.ClearToast("#webToast", 1)
                }
            }
            else if (aAction == 2) {
                if (me.Datas.Houses.PageIndex > 1)
                    me.Datas.Houses.PageIndex--;
                else {
                    flag = true;
                    $(".web-toast_content").text("当前已经是第一页");
                    Init.ClearToast("#webToast", 1)
                }
            }
            else if (aAction == 3) {
                if (me.Datas.Houses.PageIndex < me.Datas.Houses.PageCount)
                    me.Datas.Houses.PageIndex++;
                else {
                    flag = true;
                    $(".web-toast_content").text("当前已经是最后一页");
                    Init.ClearToast("#webToast", 1)
                }
            }
            else if (aAction == 4) {
                if (me.Datas.Houses.PageIndex < me.Datas.Houses.PageCount)
                    me.Datas.Houses.PageIndex = me.Datas.Houses.PageCount;
                else {
                    flag = true;
                    $(".web-toast_content").text("当前已经是末页");
                    Init.ClearToast("#webToast", 1)
                }
            }
            if (!flag)
                me.Refresh();
        }
        catch (E) {; }
    },
    //更改级联的省、市、区
    doChange: function () {
        var me = Home;
        try {
            Init.WebToast();
            me.Datas.ChooseCaption.province = $("#s_province").val();
            me.Datas.ChooseCaption.city = $("#s_city").val();
            me.Datas.ChooseCaption.district = $("#s_county").val();
            me.Refresh();
        } catch (e) {; }
    },
    //查看详情
    ShowDetail: function (aIndex) {
        var me = Home;
        try {
            Init.WebToast();
            var aInfo = me.Datas.Houses.DataList[aIndex];
            if (aInfo != null) {
                var aHtml = bt(me.Tpls.tplHouseDetail.C, { tplData: aInfo });
                hhls.fillElement(".HouseDetail", aHtml);
                var aData = {
                    UID: $("#UID").val(),
                    HNO: aInfo.HNO
                };
                //判断是否已经收藏
                Ac.acGetTable(Init.Path.Home_GetCollectState, aData, function (aRes) {
                    if (aRes.Datas[0].F_Count == 0) {
                        $("#webToast").remove();
                    }
                        //已经收藏过，更改样式，除去onclick事件
                    else {
                        $(".web-toast_content").text("这条房屋信息已经在收藏中");
                        $("#collectBtn").attr("onclick", "");
                        $("#collectBtn").removeClass("btn-danger").addClass("btn-primary");
                        hhls.fillElement("#collectBtn", '<i class="fa fa-heart"></i> 已收藏');
                        Init.ClearToast("#webToast", 1)
                    }
                })
            }
            me.Datas.HouseDetail = aInfo;
        } catch (e) {; }
    },
    //加入我的收藏
    doCollect: function () {
        var me = Home;
        try {
            Init.WebToast();
            var aInfo = {
                UID: $("#UID").val(),
                HNO: me.Datas.HouseDetail.HNO
            };
            Ac.acExecuteSql(Init.Path.Home_doCollect, aInfo, function (aRes) {
                $(".web-toast_content").text(aRes.State == 1 ? "收藏成功！" : "收藏失败！");
                if (aRes.State == 1) {
                    $("#collectBtn").attr("onclick", "");
                    $("#collectBtn").removeClass("btn-danger").addClass("btn-primary");
                    hhls.fillElement("#collectBtn", '<i class="fa fa-heart"></i> 已收藏');
                } else {
                }
                Init.ClearToast("#webToast", 1)
            })

        } catch (e) {; }
    },
    //搜索框中信息
    doSearchRefresh: function () {
        var me = Home;
        try {
            Init.WebToast();
            me.Refresh();
        } catch (e) {; }
    }
};