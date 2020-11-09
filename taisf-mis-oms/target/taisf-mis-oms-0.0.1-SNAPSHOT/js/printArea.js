(function ($) {
    var printAreaCount = 0;
    var printType=
    {
        recipeWeV   :"recipeWeV",    //处方 西药  竖版显示
        recipeChV   :"recipeChV",    //处方 中药  竖版显示
        recipeWe    :"recipeWe",     //处方 西药  横板显示
        recipeCh    :"recipeCh",     //处方 中药  横板显示
        check       :"check",        //检查单
        medicine    :"medicine",     //发药单
        retail      :"retail",       //零售单
        invoice     :"invoice"       //发票
    }
    $.fn.printArea = function (data,type,num) {
        if(!data||data.length<1)
        {
            return;
        }

        var pageInfo="";

        for(var i in data)
        {
            var item="";
            switch(type)
            {
                case printType.recipeWeV:
                    item= _createRecipeWeV(data[i],num);
                    break;
                case printType.recipeWe:
                    item= _createRecipeWe(data[i],num);
                    break;
                case printType.recipeChV:
                    item= _createRecipeChV(data[i],num);
                    break;
                case printType.recipeCh:
                    item= _createRecipeCh(data[i],num);
                    break;
                case printType.check:
                    item= _createCheck(data[i],num);
                    break;
                case printType.medicine:
                    item= _createMedicine(data[i],num);
                    break;
                case printType.retail:
                    item= _createRetail(data[i],num);
                    break;
                case printType.invoice:
                    item= _createInvoice(data[i],num);
                    break;
            }
            pageInfo=pageInfo+item;
        }
        _print(pageInfo,type); 
    }

    function _print(pageInfo,Type)
    {
        if(!pageInfo)
        {
            return;
        }
         var isIe=0;  
        if(!!window.ActiveXObject || "ActiveXObject" in window){  
          isIe = 1;  
        }  
        var idPrefix = "printArea_";
        _removePrintArea(idPrefix + printAreaCount);
        printAreaCount++;
        var iframeId = idPrefix + printAreaCount;
        //var iframeStyle = 'position:fixed;z-index:999;width: 1000px;height:400px;left:100px;top:10px;padding:0;border:0;text-align:center;background-color:#ffffff;';
        var iframeStyle = 'position:fixed;z-index:9999;width: 0px;height:0px;left:100px;top:100px;padding:0;border:0;text-align:center;background-color:#ffffff;';
        iframe = document.createElement('IFRAME');
        $(iframe).attr({
            style: iframeStyle,
            id: iframeId
        });
        document.body.appendChild(iframe);
        var doc = iframe.contentWindow.document;
        //纵向portrait  横向landscape
        if(Type==printType.recipeWeV||Type==printType.recipeChV){
            var style='<style type="text/css">@page{size:portrait;}</style>';
            doc.write(style);
        }
        else{
             var style='<style type="text/css">@page{size:landscape;}</style>';
            doc.write(style);
        }
        
        doc.write(pageInfo);
        doc.close();
        if(Type==printType.invoice)
        {
            $(doc.body).css("font-family",'SimSun');
        }
        else
        {
            $(doc.body).css("font-family",'-apple-system, "Helvetica Neue", Arial, "PingFang SC", "Hiragino Sans GB", STHeiti, "Microsoft YaHei", "Microsoft JhengHei", "Source Han Sans SC", "Noto Sans CJK SC", "Source Han Sans CN", "Noto Sans SC", "Source Han Sans TC", "Noto Sans CJK TC", "WenQuanYi Micro Hei", SimSun, sans-serif')
        }
        var frameWindow = iframe.contentWindow;
        frameWindow.close();
        frameWindow.focus();
        frameWindow.print();  
        _removePrintArea(idPrefix + printAreaCount);
    }

    function _removePrintArea (id) {
        $("iframe#" + id).remove();
    };
    
    //处方笺 西药 药品支持7行  名称/备注支持35个字
    // [{
    //     
    //     "hospitalName":"xx医院",
    //     "visCode": "20170701113",
    //     "medTreatment": "医保/自费",
    //     "medCardNum": 1427231988062732323,
    //     "prescriptionNum": "1648365",
    //     "signature": " 张明",
    //     "patientName": "张雷",
    //     "sex": "男",
    //     "age": "19",
    //     "medRecordNum": "20170703001",
    //     "department": "妇产科",
    //     "cureDate": "2017年07月05日",
    //     "phoneNum": "15210753411",住址或电话
    //     "diagnosis": "病毒性感冒",
    //     "totalMoney": "150",
    //     "list": [
    //         {
    //             "index":"1",
    //             "drugName": "asplin",
    //             "usage": "口服",
    //             "consumption": "一日1次；一次2ml，共1天",
    //             "specification": "250ml:12.5g",
    //             "num": "x5",
    //             "remark": "喝前摇一摇"
    //         },
    //         {
    //             "drugName": "dvt",
    //             "usage": "口服",
    //             "consumption": "一日1次；一次2ml，共1天",
    //             "specification": "250ml:12.5g",
    //             "num": "x5",
    //             "remark": "喝前摇一摇"
    //         }
    //     ]
    // }]
    function _createRecipeWe(data,num)
    {
        if(!data||!data.list||data.list.length<1)
        {
            return;
        }
        var header='<div style="width: 882px;height:100%;position: relative;margin:auto;padding:0;">'+
                    '<div style="padding-top: 5px;padding-bottom: 20px;text-align: center;font-size: 24px;font-weight: bold;">#HOSPITALNAME#门诊处方笺</div>'+
                    '<div style="padding:0 25px 5px 25px;border-bottom: 1px solid #000;font-weight: bold;display: flex;">'+
                        '<div style="display: inline-block;font-size: 12px;width:300px;">'+
                            '费别:<span style="font-weight: normal;">#COSTTYPE#</span>'+
                        '</div>'+
                        '<div style="display: inline-block;font-size: 12px;flex: 1;">'+
                            '医疗证 / 医保卡号：<span style="font-weight: normal;">#INSCODE#</span>'+
                        '</div>'+
                        '<div style="display: inline-block;font-size: 12px;">'+
                            '<div>处方编号：<span style="font-weight: normal;">#RECIPECODE#</span></div>'+
                        '</div>'+
                    '</div>'+
                    '<div style="padding: 5px 25px;border-bottom: 1px solid #000;font-size: 12px;font-weight: bold;">'+
                        '<div style="margin-bottom: 5px;display: flex;">'+
                            '<div style="display: inline-block;flex: 1;">姓名：<span style="font-weight: normal;">#NAME#</span></div>'+
                            '<div style="display: inline-block;flex: 1.2;">性别：<span style="font-weight: normal;">#SEX#</span></div>'+
                            '<div style="display: inline-block;width: 130px;">年龄：<span style="font-weight: normal;">#AGE#</span></div>'+
                        '</div>'+
                        '<div style="margin-bottom: 5px;display: flex;">'+
                            '<div style="display: inline-block;flex: 1;">门诊病历号：<span style="font-weight: normal;">#CASECODE#</span></div>'+
                            '<div style="display: inline-block;flex: 1.2;">科室：<span style="font-weight: normal;">#DEPARTMENT#</span></div>'+
                            '<div style="display: inline-block;width: 130px;">日期：<span style="font-weight: normal;">#DATA#</span></div>'+
                        '</div>'+
                        '<div style="display: flex;">'+
                            '<div style="display: inline-block;flex: 1;">电话：<span style="font-weight: normal;">#PHONE#</span></div>'+
                            '<div style="display: inline-block;flex: 1.2;">临床诊断：<span style="font-weight: normal;">#DIAGNOSIS#</span></div>'+
                            '<div style="display: inline-block;width: 130px;"></div>'+
                        '</div>'+
                    '</div>'+
                    '<div style="position: relative;padding-top: 10px;">'+
                        '<div style="font-size: 14px;position: absolute;top:10px;left:5px;">Rx</div>'+
                        '<div style="font-size: 13px;font-weight: bolder;padding-bottom: 10px;padding-left: 50px;display: flex;">'+
                            '<div style="display: inline-block;width: 50px;">组号</div>'+
                            '<div style="display: inline-block;flex: 2;">药品名称 / 用法用量 / 备注</div>'+
                            '<div style="display: inline-block;flex: 1;">规格</div>'+
                            '<div style="display: inline-block;width: 70px;">总量</div>'+
                        '</div>'+
                        '<div style="font-size: 12px;margin-left: 50px;">';

             var row='<div style="display: flex;margin-bottom: 10px;">'+
                                '<div style="width: 50px;padding-top: 2px;">#INDEX#</div>'+
                                '<div style="flex: 2;">'+
                                    '<span style="display: inline-block;font-size:13px;">#MNAME#</span>'+
                                    '<div style="font-weight: normal;margin-top:3px;">#USAGE#</div>'+
                                '</div>'+
                                '<div style="flex: 1;">'+
                                    '<span style="display: inline-block;width: 125px">#MSPEC#</span>'+
                                '</div>'+
                                '<div style="width: 70px;">'+
                                    '<span>#MNUM#</span>'+
                                '</div>'+
                            '</div>';
            var footer='</div>'+
                    '</div>'+
                    '<div style="font-size: 12px;position: absolute;width:240px;right:0px;bottom: 40px;">医师签名（盖章）：#DOCTORNAME#</div>'+
                    '<div style="position: absolute;bottom: 0;left:0;width: 100%;border-top: 1px solid #000;padding-top:10px;">'+
                        '<div style="font-size: 12px;margin-left: 25px;display: flex;">'+
                            '<div style="display: inline-block;flex:1;">合计金额：<span>#SUM#</span>元</div>'+
                            '<div style="display: inline-block;flex:1.2;">审核、调配药师：_______________</div>'+
                            '<div style="display: inline-block;width:240px;">核对、发药药师：______________</div>'+
                        '</div>'+
                    '</div>'+
                '</div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>6)
            {
                break;
            }
            temp=temp+row.replace(/#MNAME#/g,data.list[index].drugName)
            .replace(/#MSPEC#/g,data.list[index].specification)
            .replace(/#INDEX#/g,data.list[index].index)
            .replace(/#USAGE#/g,data.list[index].usage)
            .replace(/#MNUM#/g,data.list[index].num);
        }
        temp=temp+footer;

        temp=temp.replace(/#HOSPITALNAME#/g,data.hospitalName)
                .replace(/#ORDERCODE#/g,data.visCode)
                .replace(/#COSTTYPE#/g,data.medTreatment)
                .replace(/#INSCODE#/g,data.medCardNum)
                .replace(/#RECIPECODE#/g,data.prescriptionNum)
                .replace(/#NAME#/g,data.patientName)
                .replace(/#SEX#/g,data.sex)
                .replace(/#AGE#/g,data.age)
                .replace(/#CASECODE#/g,data.medRecordNum)
                .replace(/#DEPARTMENT#/g,data.department)
                .replace(/#DATA#/g,data.cureDate)
                .replace(/#PHONE#/g,data.phoneNum)
                .replace(/#DIAGNOSIS#/g,data.diagnosis)
                .replace(/#SUM#/g,data.totalMoney)
                .replace(/#DOCTORNAME#/g,data.signature);
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }

    //处方笺 中药 支持40种药 支持药品名称加剂量 14个字
    // {
    //     "orderNum":112,
    //     "visCode": "20170701113",
    //     "medTreatment": "医保",
    //     "medCardNum": 1427231988062732323,
    //     "prescriptionNum": "1648365",
    //     "signature": " 张明",
    //     "patientName": "张雷",
    //     "sex": "男",
    //     "age": "19",
    //     "medRecordNum": "20170703001",
    //     "department": "妇产科",
    //     "cureDate": "2017年07月05日",
    //     "phoneNum": "15210753411",
    //     "diagnosis": "病毒性感冒",
    //     "totalMoney": "150",
    //     "list": [
    //         {
    //             "drugName": "当归",
    //             "weight": "10g"
    //         },
    //         {
    //             "drugName": "陈皮",
    //             "weight": "10g"
    //         }
    //     ]
    // }
    function _createRecipeWeV(data,num)
    {
        if(!data||!data.list||data.list.length<1)
        {
            return;
        }
        var header='<div style="width:622px;height:100%;position:relative;margin:auto;padding:0;">'+
                        '<div style="font-size: 18px;position: absolute;top:0;left:10px;font-weight: bold;">#ORDERNUM#</div>'+
                        '<div style="padding-top: 20px;padding-bottom: 10px;text-align: center;font-size: 26px;font-weight: bold;">#HOSPITALNAME#</div>'+
                        '<div style="padding-bottom: 20px;text-align: center;font-size: 22px;font-weight: bold;">处方笺</div>'+
                        '<div style="padding:0 10px;border-bottom: 1px solid #000;font-weight: bold;display: flex;">'+
                            '<div style="display: inline-block;font-size: 14px;margin-right: 33px">'+
                                '<div style="float: left;margin-top: 7px;">费别：</div>'+
                                '<div style="float: left;">'+
                                    '<div style="float: left;">'+
                                        //'<div style="float: left;width:12px;height:12px;border: 1px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>'+
                                        '#zifei#'+
                                        '<div style="float: left;margin-bottom: 6px;margin-top: -4px;">自费</div>'+
                                    '</div>'+
                                    '<div>'+
                                        //<div style="float: left;width:0;height:0;border:7px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>'+
                                        '#yibao#'+
                                        '<div style="float: left;margin-bottom: 6px;margin-top: -3px;">医保</div>'+
                                    '</div>'+
                                '</div>'+
                            '</div>'+
                            '<div style="display: inline-block;font-size: 14px;margin-right: 33px;margin-top: 7px;flex:1;">医保卡号：<span style="font-weight: normal;">#INSCODE#</span></div>'+
                            '<div style="display: inline-block;font-size: 14px;margin-top: 7px;">'+
                                '<div>处方号：<span style="font-weight: normal;">#RECIPECODE#</span></div>'+
                            '</div>'+
                        '</div>'+
                        '<div style="padding: 5px 10px;border-bottom: 1px solid #000;font-size: 14px;font-weight: bold;">'+
                            '<div style="margin-bottom: 10px;display: flex;">'+
                                '<div style="display: inline-block;width: 253px;">姓名：<span style="font-weight: normal;">#NAME#</span></div>'+
                                '<div style="display: inline-block;flex: 1.2;">性别：<span style="font-weight: normal;">#SEX#</span></div>'+
                                '<div style="display: inline-block;width: 138px;">年龄：<span style="font-weight: normal;">#AGE#</span></div>'+
                            '</div>'+
                            '<div style="margin-bottom: 10px;display: flex;">'+
                                '<div style="display: inline-block;width: 253px;">就诊科室：<span style="font-weight: normal;">#DEPARTMENT#</span></div>'+
                                '<div style="display: inline-block;flex:1";>开具日期：<span style="font-weight: normal;">#DATA#</span></div>'+
                            '</div>'+
                             '<div style="display: flex;margin-bottom: 10px">'+
                                '<div style="flex: 1;">临床诊断：<span style="font-weight: normal;">#DIAGNOSIS#</span></div>'+
                            '</div>'+
                            '<div style="display: flex;">'+
                                '<div style="flex: 1;">住址 / 电话：<span style="font-weight: normal;">#PHONE#</span></div>'+
                            '</div>'+
                        '</div>'+
                        '<div style="position: relative;padding-top: 10px;">'+
                    '<div style="font-size: 20px;padding-bottom: 10px;padding-left: 10px;">Rx</div>'+
                    '<div style="font-size: 13px;font-weight: bolder;padding:0 20px 0 20px;display: flex;">'+
                        '<div style="display: inline-block;width: 50px;">组号</div>'+
                      '<div style="display: inline-block;flex: 2;">药品名称 / 规格</div>'+
                        '<div style="display: inline-block;width: 70px;">数量</div>'+
                    '</div>';
                    var row= '<div style="font-size: 13px;margin-left: 20px;margin-top: 20px;">'+
                        '<div style="display: flex;margin-bottom: 10px;padding-right: 20px;">'+
                            '<div style="width: 50px;padding-top: 2px;">#INDEX#</div>'+
                            '<div style="flex: 2;">'+
                                '<span style="display: inline-block;font-size:13px;font-weight: bolder;">#MNAME#</span>'+
                                '<div style="margin-top:3px;font-size:12px;">#USAGE#</div>'+
                            '</div>'+
                            '<div style="width: 70px;">'+
                                '<span>#MNUM#</span>'+
                            '</div>'+
                        '</div>'+
                    '</div>';
    var footer='</div>'+
                        '<div style="position: absolute;bottom: 0;left:0;width: 100%;border-top: 1px solid #000;padding-top:10px;">'+
                            '<div style="font-size: 13px;margin-left: 25px;display: flex;margin-bottom: 20px;">'+
                                '<div style="display: inline-block;width: 160px;margin-right: 43px;">医师：<span>#DOCTORNAME#</span></div>'+
                                '<div style="display: inline-block;flex:1;">药品金额：<span>#SUM#</span>元</div>'+
                                '<div style="display: inline-block;width:160px;">其他费用：__________</div>'+
                            '</div>'+
                            '<div style="font-size: 13px;margin-left: 25px;display: flex;margin-bottom: 20px;">'+
                                '<div style="display: inline-block;width: 160px;margin-right: 43px;">总金额：____________</div>'+
                                '<div style="display: inline-block;flex:1.2;">其中自费：_______________</div>'+
                                '<div style="display: inline-block;width:160px;">报销：______________</div>'+
                            '</div>'+
                            '<div style="font-size: 13px;margin-left: 25px;display: flex;margin-bottom: 20px;">'+
                                '<div style="display: inline-block;width: 160px;margin-right: 43px;">配剂：______________</div>'+
                                '<div style="display: inline-block;flex:1;">核对发药：_______________</div>'+
                            '</div>'+
                        '</div>'+
                    '</div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>6)
            {
                break;
            }

            temp=temp+row.replace(/#MNAME#/g,data.list[index].drugName)
            .replace(/#MSPEC#/g,data.list[index].specification)
            .replace(/#INDEX#/g,data.list[index].index)
            .replace(/#USAGE#/g,data.list[index].usage)
            .replace(/#MNUM#/g,data.list[index].num);
        }
        temp=temp+footer;

        temp=temp.replace(/#ORDERNUM#/g,data.orderNum)
                .replace(/#HOSPITALNAME#/g,data.hospitalName)
                .replace(/#ORDERCODE#/g,data.visCode)
                .replace(/#COSTTYPE#/g,data.medTreatment)
                .replace(/#INSCODE#/g,data.medCardNum)
                .replace(/#RECIPECODE#/g,data.prescriptionNum)
                .replace(/#NAME#/g,data.patientName)
                .replace(/#SEX#/g,data.sex)
                .replace(/#AGE#/g,data.age)
                .replace(/#CASECODE#/g,data.medRecordNum)
                .replace(/#DEPARTMENT#/g,data.department)
                .replace(/#DATA#/g,data.cureDate)
                .replace(/#PHONE#/g,data.phoneNum)
                .replace(/#DIAGNOSIS#/g,data.diagnosis)
                .replace(/#SUM#/g,data.totalMoney)
                .replace(/#DOCTORNAME#/g,data.signature);
                var checkedDiv='<div style="float: left;width:0;height:0;border:7px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>';
                var UncheckedDiv='<div style="float: left;width:12px;height:12px;border: 1px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>';
                if(data.medTreatment){
                   temp=temp.replace(/#zifei#/g,UncheckedDiv)
                   .replace(/#yibao#/g,checkedDiv)
                }else{
                    temp=temp.replace(/#zifei#/g,checkedDiv)
                   .replace(/#yibao#/g,UncheckedDiv)
                }
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }

    //处方笺 中药 支持42种药 支持药品名称加剂量 12个字
    // [{
    //     "hospitalName":"xx医院",
    //     "visCode": "20170701113",
    //     "medTreatment": "true/false",true医保false自费
    //     "medCardNum": 1427231988062732323,
    //     "prescriptionNum": "1648365",
    //     "signature": " 张明",
    //     "patientName": "张雷",
    //     "sex": "男",
    //     "age": "19",
    //     "medRecordNum": "20170703001",
    //     "department": "妇产科",
    //     "cureDate": "2017年07月05日",
    //     "phoneNum": "15210753411",
    //     "diagnosis": "病毒性感冒",
    //     "totalMoney": "150",
    //     "list": [
    //         {
    //             "drugName": "当归",
    //             "weight": "10g"
    //         },
    //         {
    //             "drugName": "陈皮",
    //             "weight": "10g"
    //         }
    //     ]
    // }]
    function _createRecipeCh(data,num)
    {
        if(!data||!data.list||data.list.length<1)
        {
            return;
        }
        var header='<div style="width: 882px;height:100%;;position: relative;margin:auto;padding:0;">'+
                    '<div style="padding-top: 5px;padding-bottom: 20px;text-align: center;font-size: 24px;font-weight: bold;">#HOSPITALNAME#门诊处方笺</div>'+
                    '<div style="padding:0 25px 5px 25px;border-bottom: 1px solid #000;font-weight: bold;display: flex;">'+
                        '<div style="display: inline-block;font-size: 12px;width:300px;">'+
                            '费别:<span style="font-weight: normal;">#COSTTYPE#</span>'+
                        '</div>'+
                        '<div style="display: inline-block;font-size: 12px;flex: 1;">'+
                            '医疗证 / 医保卡号：<span style="font-weight: normal;">#INSCODE#</span>'+
                        '</div>'+
                        '<div style="display: inline-block;font-size: 12px;">'+
                            '<div>处方编号：<span style="font-weight: normal;">#RECIPECODE#</span></div>'+
                        '</div>'+
                    '</div>'+
                    '<div style="padding: 5px 25px;border-bottom: 1px solid #000;font-size: 12px;font-weight: bold;">'+
                        '<div style="margin-bottom: 5px;display: flex;">'+
                            '<div style="display: inline-block;flex: 1;">姓名：<span style="font-weight: normal;">#NAME#</span></div>'+
                            '<div style="display: inline-block;flex: 1.2;">性别：<span style="font-weight: normal;">#SEX#</span></div>'+
                            '<div style="display: inline-block;width: 130px;">年龄：<span style="font-weight: normal;">#AGE#</span></div>'+
                        '</div>'+
                        '<div style="margin-bottom: 5px;display: flex;">'+
                            '<div style="display: inline-block;flex: 1;">门诊病历号：<span style="font-weight: normal;">#CASECODE#</span></div>'+
                            '<div style="display: inline-block;flex: 1.2;">科室：<span style="font-weight: normal;">#DEPARTMENT#</span></div>'+
                            '<div style="display: inline-block;width: 130px;">日期：<span style="font-weight: normal;">#DATA#</span></div>'+
                        '</div>'+
                        '<div style="display: flex;">'+
                            '<div style="display: inline-block;flex: 1;">电话：<span style="font-weight: normal;">#PHONE#</span></div>'+
                            '<div style="display: inline-block;flex: 1.2;">临床诊断：<span style="font-weight: normal;">#DIAGNOSIS#</span></div>'+
                            '<div style="display: inline-block;width: 130px;"></div>'+
                        '</div>'+
                    '</div>'+
                    '<div style="position: relative;padding-top: 10px;">'+
                        '<div style="font-size: 14px;position: absolute;top:10px;left:5px;">Rx</div>'+
                        '<div style="font-size: 0;margin:37px 25px 0 30px ">';

             var row='<div style="width: 25%;font-size: 12px;display: inline-block;height:30px;'+
             'padding:0 10px;box-sizing: border-box;">#MNAME#：#MWEIGHT#</div>';
                                
            var footer='</div>'+
                    '</div>'+
                    '<div style="font-size: 15px;position: absolute;left:25px;bottom: 80px;padding-right: 25px;">#USAGE#</div>'+
                    '<div style="font-size: 12px;position: absolute;width:240px;right:0px;bottom: 40px;">医师签名（盖章）：#DOCTORNAME#</div>'+
                    '<div style="position: absolute;bottom: 0;left:0;width: 100%;border-top: 1px solid #000;padding-top:10px;">'+
                        '<div style="font-size: 12px;margin-left: 25px;display: flex;">'+
                            '<div style="display: inline-block;flex:1;">合计金额：<span>#SUM#</span>元</div>'+
                            '<div style="display: inline-block;flex:1.2;">审核、调配药师：_______________</div>'+
                            '<div style="display: inline-block;width:240px;">核对、发药药师：______________</div>'+
                        '</div>'+
                    '</div>'+
                '</div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>39)
            {
                break;
            }
            temp=temp+row.replace(/#MNAME#/g,data.list[index].drugName)
            .replace(/#MWEIGHT#/g,data.list[index].weight);
        }
        temp=temp+footer;

        temp=temp.replace(/#HOSPITALNAME#/g,data.hospitalName)
                .replace(/#ORDERCODE#/g,data.visCode)
                .replace(/#COSTTYPE#/g,data.medTreatment)
                .replace(/#INSCODE#/g,data.medCardNum)
                .replace(/#RECIPECODE#/g,data.prescriptionNum)
                .replace(/#NAME#/g,data.patientName)
                .replace(/#SEX#/g,data.sex)
                .replace(/#AGE#/g,data.age)
                .replace(/#CASECODE#/g,data.medRecordNum)
                .replace(/#DEPARTMENT#/g,data.department)
                .replace(/#DATA#/g,data.cureDate)
                .replace(/#PHONE#/g,data.phoneNum)
                .replace(/#DIAGNOSIS#/g,data.diagnosis)
                .replace(/#SUM#/g,data.totalMoney)
                .replace(/#USAGE#/g,data.usage)
                .replace(/#DOCTORNAME#/g,data.signature);
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }
     function _createRecipeChV(data,num)
    {
        if(!data||!data.list||data.list.length<1)
        {
            return;
        }
        var header='<div style="width:622px;height:100%;position:relative;margin:auto;padding:0;">'+
                        '<div style="font-size: 18px;position: absolute;top:0;left:10px;font-weight: bold;">#ORDERNUM#</div>'+
                        '<div style="padding-top: 20px;padding-bottom: 10px;text-align: center;font-size: 26px;font-weight: bold;">#HOSPITALNAME#</div>'+
                        '<div style="padding-bottom: 20px;text-align: center;font-size: 22px;font-weight: bold;">处方笺</div>'+
                        '<div style="padding:0 10px;border-bottom: 1px solid #000;font-weight: bold;display: flex;">'+
                            '<div style="display: inline-block;font-size: 14px;margin-right: 33px">'+
                                '<div style="float: left;margin-top: 7px;">费别：</div>'+
                                '<div style="float: left;">'+
                                    '<div style="float: left;">'+
                                        //'<div style="float: left;width:12px;height:12px;border: 1px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>'+
                                        '#zifei#'+
                                        '<div style="float: left;margin-bottom: 6px;margin-top: -4px;">自费</div>'+
                                    '</div>'+
                                    '<div>'+
                                        //<div style="float: left;width:0;height:0;border:7px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>'+
                                        '#yibao#'+
                                        '<div style="float: left;margin-bottom: 6px;margin-top: -3px;">医保</div>'+
                                    '</div>'+
                                '</div>'+
                            '</div>'+
                            '<div style="display: inline-block;font-size: 14px;margin-right: 33px;margin-top: 7px;flex:1;">医保卡号：<span style="font-weight: normal;">#INSCODE#</span></div>'+
                            '<div style="display: inline-block;font-size: 14px;margin-top: 7px;">'+
                                '<div>处方号：<span style="font-weight: normal;">#RECIPECODE#</span></div>'+
                            '</div>'+
                        '</div>'+
                        '<div style="padding: 5px 10px;border-bottom: 1px solid #000;font-size: 14px;font-weight: bold;">'+
                            '<div style="margin-bottom: 10px;display: flex;">'+
                                '<div style="display: inline-block;width: 253px;">姓名：<span style="font-weight: normal;">#NAME#</span></div>'+
                                '<div style="display: inline-block;flex: 1.2;">性别：<span style="font-weight: normal;">#SEX#</span></div>'+
                                '<div style="display: inline-block;width: 138px;">年龄：<span style="font-weight: normal;">#AGE#</span></div>'+
                            '</div>'+
                            '<div style="margin-bottom: 10px;display: flex;">'+
                                '<div style="display: inline-block;width: 253px;">就诊科室：<span style="font-weight: normal;">#DEPARTMENT#</span></div>'+
                                '<div style="display: inline-block;flex:1";>开具日期：<span style="font-weight: normal;">#DATA#</span></div>'+
                            '</div>'+
                             '<div style="display: flex;margin-bottom: 10px">'+
                                '<div style="flex: 1;">临床诊断：<span style="font-weight: normal;">#DIAGNOSIS#</span></div>'+
                            '</div>'+
                            '<div style="display: flex;">'+
                                '<div style="flex: 1;">住址 / 电话：<span style="font-weight: normal;">#PHONE#</span></div>'+
                            '</div>'+
                        '</div>'+
                        '<div style="position: relative;padding-top: 10px;;">'+
                    '<div style="font-size: 20px;padding-bottom: 10px;padding-left: 10px;">Rx</div><div style="padding:0 20px;font-size:0">';

             var row=' <div style="width: 33.3333%;font-size: 13px;display: inline-block;height:30px;box-sizing: border-box;">#MNAME#：#MWEIGHT#</div>';
                                
            var footer='</div></div>'+
                        '<div style="font-size: 15px;position: absolute;left:25px;bottom: 135px;padding-right: 25px;">#USAGE#</div>'+
                        '<div style="position: absolute;bottom: 0;left:0;width: 100%;border-top: 1px solid #000;padding-top:10px;">'+
                            '<div style="font-size: 13px;margin-left: 25px;display: flex;margin-bottom: 20px;">'+
                                '<div style="display: inline-block;width: 160px;margin-right: 43px;">医师：<span>#DOCTORNAME#</span></div>'+
                                '<div style="display: inline-block;flex:1;">药品金额：<span>#SUM#</span>元</div>'+
                                '<div style="display: inline-block;width:160px;">其他费用：__________</div>'+
                            '</div>'+
                            '<div style="font-size: 13px;margin-left: 25px;display: flex;margin-bottom: 20px;">'+
                                '<div style="display: inline-block;width: 160px;margin-right: 43px;">总金额：____________</div>'+
                                '<div style="display: inline-block;flex:1.2;">其中自费：_______________</div>'+
                                '<div style="display: inline-block;width:160px;">报销：______________</div>'+
                            '</div>'+
                            '<div style="font-size: 13px;margin-left: 25px;display: flex;margin-bottom: 20px;">'+
                                '<div style="display: inline-block;width: 160px;margin-right: 43px;">配剂：______________</div>'+
                                '<div style="display: inline-block;flex:1;">核对发药：_______________</div>'+
                            '</div>'+
                        '</div>'+
                    '</div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>41)
            {
                break;
            }
            temp=temp+row.replace(/#MNAME#/g,data.list[index].drugName)
            .replace(/#MWEIGHT#/g,data.list[index].weight);
        }
        temp=temp+footer;

        temp=temp.replace(/#ORDERNUM#/g,data.orderNum)
                .replace(/#HOSPITALNAME#/g,data.hospitalName)
                .replace(/#ORDERCODE#/g,data.visCode)
                .replace(/#COSTTYPE#/g,data.medTreatment)
                .replace(/#INSCODE#/g,data.medCardNum)
                .replace(/#RECIPECODE#/g,data.prescriptionNum)
                .replace(/#NAME#/g,data.patientName)
                .replace(/#SEX#/g,data.sex)
                .replace(/#AGE#/g,data.age)
                .replace(/#CASECODE#/g,data.medRecordNum)
                .replace(/#DEPARTMENT#/g,data.department)
                .replace(/#DATA#/g,data.cureDate)
                .replace(/#PHONE#/g,data.phoneNum)
                .replace(/#DIAGNOSIS#/g,data.diagnosis)
                .replace(/#SUM#/g,data.totalMoney)
                .replace(/#USAGE#/g,data.usage)
                .replace(/#DOCTORNAME#/g,data.signature);
                
                var checkedDiv='<div style="float: left;width:0;height:0;border:7px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>';
                var UncheckedDiv='<div style="float: left;width:12px;height:12px;border: 1px solid #000;margin-right: 5px;margin-bottom: 6px;"></div>';
                if(data.medTreatment){
                   temp=temp.replace(/#zifei#/g,UncheckedDiv)
                   .replace(/#yibao#/g,checkedDiv)
                }else{
                    temp=temp.replace(/#zifei#/g,checkedDiv)
                   .replace(/#yibao#/g,UncheckedDiv)
                }
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }

    //申请单 治疗单 支持12行检查项目  项目名称18字 备注23字
    // [{
    //     "hospitalName":"xx医院",
    //     "visCode": "20170701113",
    //     "patientName": "张雷",
    //     "sex": "男",
    //     "age": "19",
    //     "sex": "男",
    //     "medRecordNum": "20170703001",
    //     "department": "妇产科",
    //     "cureDate": "2017年07月05日",
    //     "phoneNum": "15210753411",
    //     "diagnosis": "病毒性感冒",
    //     "physician": "段修翠",
    //     "totalMoney": "150",
    //     "date": "2017-07-05 12:23",
    //     "list": [
    //         {
    //             "index":"1",
    //             "itemName": "血清前白蛋白[PAB]",
    //             "count": "5",
    //             "remark": "腹部",
    //             "amout":"11"
    //         },
    //         {
    //             "index":"1",
    //             "itemName": "血清前白蛋白[PAB]",
    //             "count": "6",
    //             "remark": "腹部",
    //             "amout":"11"
    //         }
    //     ]
    // }]
     function _createCheck(data,num)
    {
        var header='<div style="width: 802px;height:100%;position: relative;margin:auto;">\
                    <div style="padding-top: 15px;padding-bottom: 20px;text-align: center;font-size: 24px;font-weight: bold;">#HOSPITALNAME#</div>\
                    <div style="padding: 5px 25px;border-bottom: 1px solid #000;border-top: 1px solid #000;font-size: 12px;font-weight: bold;">\
                        <div style="margin-bottom: 5px;display: flex;">\
                            <div style="display: inline-block;flex: 1;">姓名：<span style="font-weight: normal;">#NAME#</span></div>\
                            <div style="display: inline-block;flex: 1.2;">性别：<span style="font-weight: normal;">#SEX#</span></div>\
                            <div style="display: inline-block;width: 130px;">年龄：<span style="font-weight: normal;">#AGE#</span></div>\
                        </div>\
                        <div style="margin-bottom: 5px;display: flex;">\
                            <div style="display: inline-block;flex: 1;">门诊病历号：<span style="font-weight: normal;">#CASECODE#</span></div>\
                            <div style="display: inline-block;flex: 1.2;">科室：<span style="font-weight: normal;">#DEPARTMENT#</span></div>\
                            <div style="display: inline-block;width: 130px;">日期：<span style="font-weight: normal;">#DATA#</span></div>\
                        </div>\
                        <div style="display: flex;">\
                            <div style="display: inline-block;flex: 1;">电话：<span style="font-weight: normal;">#PHONE#</span></div>\
                            <div style="display: inline-block;flex: 1.2;">临床诊断：<span style="font-weight: normal;">#DIAGNOSIS#</span></div>\
                            <div style="display: inline-block;width: 130px;"></div>\
                        </div>\
                       </div>\
                    <div style="position: relative;padding-top: 10px;">\
                        <div style="font-size: 13px;font-weight: bold;padding-bottom: 10px;padding-left: 60px;display: flex;">\
                            <div style="display: inline-block;flex: 1;">项目名称</div>\
                            <div style="display: inline-block;flex: 1.2;">备注</div>\
                            <div style="display: inline-block;width: 80px;">次数</div>\
                            <div style="display: inline-block;width: 90px;">金额</div>\
                        </div>\
                        <div style="font-size: 13px;margin-left: 25px;">';
        var row='<div style="display: flex;margin-bottom: 10px;">\
                                <span style="width: 35px;">#INDEX#</span>\
                                <span style="display: inline-block;flex: 1;">#PNAME#</span>\
                                <span style="display: inline-block;flex: 1.2;">#PMARK#</span>\
                                <span style="display: inline-block;width: 80px;">#PNUM#</span>\
                                <span style="display: inline-block;width: 90px;">#PAMOUT#</span>\
                            </div>';
                            
        var footer='</div>\
                    </div>\
                    <div style="position: absolute;bottom: 0;left:0;width: 100%;border-top: 1px solid #000;padding-top:10px;">\
                        <div style="font-size: 12px;margin-left: 25px;display: flex;">\
                            <div style="display: inline-block;flex: 1;">合计金额：<span>#SUM#</span>元</div>\
                            <div style="display: inline-block;flex: 1;">医师：<span>#DOCTORNAME#</span></div>\
                            <div style="display: inline-block;text-align:right;width: 200px;margin-right: 25px;">开单日期：<span>#CREATEDATA#</span></div>\
                        </div>\
                    </div>\
                </div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>11)
            {
                break;
            }
            temp=temp+row.replace(/#PNAME#/g,data.list[index].itemName)
            .replace(/#INDEX#/g,data.list[index].index)
            .replace(/#PMARK#/g,data.list[index].remark)
            .replace(/#PNUM#/g,data.list[index].count)
            .replace(/#PAMOUT#/g,data.list[index].amout);
        }
        temp=temp+footer;

        temp=temp.replace(/#HOSPITALNAME#/g,data.hospitalName)
                .replace(/#ORDERCODE#/g,data.visCode)
                .replace(/#NAME#/g,data.patientName)
                .replace(/#SEX#/g,data.sex)
                .replace(/#AGE#/g,data.age)
                .replace(/#CASECODE#/g,data.medRecordNum)
                .replace(/#DEPARTMENT#/g,data.department)
                .replace(/#DATA#/g,data.cureDate)
                .replace(/#PHONE#/g,data.phoneNum)
                .replace(/#DIAGNOSIS#/g,data.diagnosis)
                .replace(/#SUM#/g,data.totalMoney)
                .replace(/#DOCTORNAME#/g,data.physician)
                .replace(/#CREATEDATA#/g,data.date);
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }

    //发药单  支持14行数据
    // [{
    //     "hospitalName":"xx医院",
    //     "documentNum": "JK170726170909000",    发药单号
    //     "patientName": 张雷,                   姓名
    //     "cureDate": "2017-07-26",              时间
    //     "dispensephar": "超级管理员",          发药师
    //     "list": [
    //         {
    //             "index":"1",
    //             "drugName": "yyyyyy2",
    //             "specification": "pack2",
    //             "num": "yyyyyy2"
    //         }
    //     ]
    // }]
     function _createMedicine(data,num)
    {
        if(!data||!data.list||data.list.length<1)
        {
            return;
        }
        var header='<div style="width: 802px;height:100%;position: relative;margin:auto;">\
                    <div style="padding-top: 15px;padding-bottom: 20px;text-align: center;font-size: 24px;font-weight: bold;">#HOSPITALNAME#发药单</div>\
                    <div style="padding-bottom: 5px;border-bottom: 1px solid #000;font-weight: bold;display: flex;">\
                        <div style="display: inline-block;font-size: 12px;margin-left: 25px;">\
                            单据编号:<span style="font-weight: normal;">#NUMBER#</span>\
                        </div>\
                        <div style="display: inline-block;font-size: 12px;flex: 1;text-align: center;">\
                            客户姓名：<span style="font-weight: normal;">#NAME#</span>\
                        </div>\
                        <div style="display: inline-block;font-size: 12px;margin-right: 25px;">\
                            <div>出库日期：<span style="font-weight: normal;">#SENDDATA#</span></div>\
                        </div>\
                    </div>\
                    <div style="position: relative;padding-top: 10px;">\
                        <div style="font-size: 13px;font-weight: bold;padding-bottom: 10px;padding-left: 55px;display: flex;">\
                            <div style="display: inline-block;flex: 1.8;">药品名称</div>\
                            <div style="display: inline-block;flex: 1;">规格</div>\
                            <div style="display: inline-block;width: 100px;">总量</div>\
                        </div>\
                        <div style="font-size: 12px;margin-left: 25px;">';
         var row='<div style="display: flex;margin-bottom: 12px">\
                                <div style="width: 30px;padding-top: 2px;">#INDEX#</div>\
                                <div style="flex: 1.8;">\
                                    <span style="display: inline-block;font-size:13px;">#MNAME#</span>\
                                </div>\
                                <div style="flex: 1;">\
                                    <span style="display: inline-block;width: 125px">#MSPEC#</span>\
                                </div>\
                                <div style="width: 100px;">\
                                    <span>#MNUM#</span>\
                                </div>\
                            </div>';
        var footer='</div>\
                    </div>\
                    <div style="position: absolute;bottom: 0;left:0;box-sizing:border-box;width: 100%;border-top: 1px solid #000;padding:  10px 25px 0 25px;text-align: right;">\
                        <div style="font-size: 12px;display: inline-block;">\
                            发药药师：<span>#DOCTORNAME#</span>\
                        </div>\
                    </div>\
                </div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>12)
            {
                break;
            }
            temp=temp+row.replace(/#MNAME#/g,data.list[index].drugName)
            .replace(/#MSPEC#/g,data.list[index].specification)
            .replace(/#INDEX#/g,data.list[index].index)
            .replace(/#MNUM#/g,data.list[index].num);
        }
        temp=temp+footer;
        temp=temp.replace(/#NUMBER#/g,data.documentNum)
                .replace(/#HOSPITALNAME#/g,data.hospitalName)
                .replace(/#NAME#/g,data.patientName)
                .replace(/#SENDDATA#/g,data.cureDate)
                .replace(/#DOCTORNAME#/g,data.dispensephar);
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }

    // 零售单 药品支持10行 每行支持32个字
    // {
    //    "hospitalName":'广州市白云区景泰医院',
    //     "visCode": "20170701113",
    //     "medTreatment": 170705001,
    //     "medCardNum": "2017-07-05",
    //     "prescriptionNum": "药管A",
    //     "sum":250,
    //     "list": [
    //         {
    //           'index':"1",
    //             "drugName": "布地奈德鼻喷雾剂_雷诺考特",
    //             // "usage": "饭后口服；一人一次，一次2ml；（和前摇一摇）",
    //             "specification": "10小包*12cm*20支",
    //             "num": "×2",
    //             "remark": "喝前摇一摇",
    //             "jine":"25.00"
    //         },
    //         {
    //             'index':"1",
    //             "drugName": "布地奈德鼻喷雾剂_雷诺考特",
    //             // "usage": "饭后口服；一人一次，一次2ml；（和前摇一摇）",
    //             "specification": "10小包*12cm*20支",
    //             "num": "×2",
    //             "remark": "喝前摇一摇",
    //             "jine":"25.00"
    //         }
    //     ]
    // }
     function _createRetail(data,num)
    {
        if(!data||!data.list||data.list.length<1)
        {
            return;
        }
        var header='<div style="width: 882px;height:100%;position: relative;margin:auto;padding:0;">'+
                    '<div style="padding-bottom: 40px;text-align: center;font-size: 26px;font-weight: bold;">#HOSPITALNAME#药品零售单</div>'+
                    '<div style="padding:0 25px 10px 15px;border-bottom: 2px solid #000;font-weight: bold;display: flex;">'+
                        '<div style="display: inline-block;font-size: 13px;width:353px;">单据编号:<span style="font-weight: normal;">#COSTTYPE#</span>'+
                        '</div>'+
                        '<div style="display: inline-block;font-size: 13px;flex: 1;">订单日期：<span style="font-weight: normal;">#INSCODE#</span></div>'+
                        '<div style="display: inline-block;font-size: 13px;">'+
                            '<div>创建用户：<span style="font-weight: normal;">#RECIPECODE#</span></div>'+
                        '</div>'+
                    '</div>'+
                    '<div style="position: relative;padding-top: 20px;">'+
                        '<div style="font-size: 13px;font-weight: bolder;padding-bottom: 20px;padding-left: 20px;display: flex;">'+
                            '<div style="display: inline-block;width: 60px;">编号</div>'+
                            '<div style="display: inline-block;flex: 7.2;">药品名称</div>'+
                            '<div style="display: inline-block;flex: 2.8;">规格</div>'+
                            '<div style="display: inline-block;width: 104px;">数量</div>'+
                            '<div style="display: inline-block;width: 104px;">金额（元）</div>'+
                        '</div>'+
                        '<div style="font-size: 12px;margin-left: 20px;">';

             var row='<div style="display: flex;margin-bottom: 20px;">'+
                                '<div style="width: 60px;padding-top: 2px;">#INDEX#</div>'+
                                '<div style="flex: 7.2;">'+
                                    '<span style="display: inline-block;font-size:13px;">#MNAME#</span>'+
                                '</div>'+
                                '<div style="flex: 2.8;">'+
                                    '<span style="display: inline-block;width: 125px">#MSPEC#</span>'+
                                '</div>'+
                                '<div style="width: 104px;">'+
                                    '<span>#MNUM#</span>'+
                                '</div>'+
                                '<div style="width: 104px;">'+
                                    '<span>#JINE#</span>'+
                                '</div>'+
                            '</div>';
            var footer='</div>'+
                    '</div>'+
                    '<div style="position: absolute;bottom: 0;left:0;width: 100%;border-top: 2px solid #000;padding-top:10px;">'+
                        '<div style="font-size: 13px;margin-left: 20px;">'+
                            '<div style="margin-top: 10px;margin-bottom: 10px;">合计金额：<span>#SUM#</span>元</div>'+
                            '<div style="display: flex;">'+
                                '<div style="display: inline-block;flex: 1;">审核药师：__________________</div>'+
                                '<div style="display: inline-block;width:220px;">核对、发药药师：_______________</div>'+
                            '</div>'+
                        '</div>'+
                    '</div>'+
                '</div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>9)
            {
                break;
            }
            temp=temp+row.replace(/#MNAME#/g,data.list[index].productName)
            .replace(/#MSPEC#/g,data.list[index].packing)
            .replace(/#INDEX#/g,data.list[index].index)
            .replace(/#MNUM#/g,data.list[index].amout)
            .replace(/#JINE#/g,data.list[index].price);
        }
        temp=temp+footer;

        temp=temp.replace(/#HOSPITALNAME#/g,data.hospitalName)
                .replace(/#ORDERCODE#/g,data.visCode)
                .replace(/#COSTTYPE#/g,data.orderCode)
                .replace(/#INSCODE#/g,data.orderDate)
                .replace(/#RECIPECODE#/g,data.createName)
                .replace(/#SUM#/g,data.totalMoney);
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }

   // 发票 项目支持最大长度 名称6位 价格5位 注射费注射费： 132.50，项目最多24个
   // {
   //      "curDate": "2017-08-19",         //开票日期
   //      "industryCategory": "医药",      //行业分类
   //      "customerName": "张雷",          //姓名
   //      "settlementTypeName": "自费",    //结算方式
   //      "chargeCode": "170818",          //序列号
   //      "totalMoneyUpper": null,         //合计大写
   //      "totalMoney": "32.50",           //合计小写
   //      "socialPubMoney": "0.00",        //社保金额
   //      "selfMoney": "32.50",            //自费金额
   //      "doctorCode": "4000",            //医生编号
   //      "createCode": "4000",            //收费员
   //      "list": [
   //          {
   //              "chargeItemName": "注射费", //项目名
   //              "price": "32.50"            //项目金额
   //          }
   //      ]
   //  }
   function _createInvoice(data,num)
    {
        if(!data||!data.list||data.list.length<1)
        {
            return;
        }
        var header='<div style="height: 100%;width: 590px;box-sizing:border-box;position: relative;margin:auto;padding-bottom:7px;">\
            <div style="height: 100%;width: 100%;position: relative;">\
                <div style="height:25px;line-height: 25px;width: 100%;position: absolute;bottom: 250px;font-size: 14px;">\
                    <span style="display:inline-block;position: absolute;left:76px;">#DATA#</span>\
                    <span style="display:inline-block;position: absolute;left:302px;">#TRADETYPE#</span>\
                </div>\
                <div style="height: 241px;width: 100%;position: absolute;bottom: 7px;font-size: 0;padding-left: 30px;box-sizing: border-box;">\
                    <div style="height: 22px;line-height: 22px;width: 100%;display: flex;font-size: 14px;">\
                        <div style="flex: 1;">\
                            <span>姓名:</span>\
                            <span>#PNAME#</span>\
                        </div>\
                        <div style="flex: 1;">\
                            <span>结算方式:</span>\
                            <span>#BALANCETYPE#</span>\
                        </div>\
                        <div style="flex: 1;">\
                            <span>序列号:</span>\
                            <span>#INDEX#</span>\
                        </div>\
                    </div>\
                    <div style="height: 150px;width: 100%;font-size: 0;">';

        var row='<div style="display: inline-block;font-size: 14px;width: 25%;height: 25px;line-height: 25px;">\
                    <span>#INAME#:</span>\
                    <span>#IMONEY#</span>\
                 </div>';
        var footer='</div>\
                    <div style="height: 22px;line-height: 22px;width: 100%;display: flex;font-size: 14px;">\
                        <div style="flex: 1.5;">\
                            <span>合计（大写）:</span>\
                            <span>#TOTALCAP#</span>\
                        </div>\
                        <div style="flex: 1;">\
                            <span>￥#TOTALLOW#</span>\
                        </div>\
                    </div>\
                    <div style="height: 22px;line-height: 22px;width: 100%;display: flex;font-size: 14px;">\
                        <div style="flex: 1.5;">\
                            <span>社保 / 公费记账金额:</span>\
                            <span>#SOCIALMONEY#</span>\
                        </div>\
                        <div style="flex: 1;">\
                            <span>个人缴费金额:</span>\
                            <span>#PERSONALMONEY#</span>\
                        </div>\
                    </div>\
                    <div style="height: 25px;line-height: 25px;width: 100%;text-align:center;font-size: 14px;">\
                        <div style="display:inline-block;">\
                            <div style="float: left;margin-right: 100px;">\
                            <span>医生:</span>\
                            <span>#DOCTOR#</span>\
                        </div>\
                        <div style="float: right;">\
                            <span>收费员:</span>\
                            <span>#TOLLCOLLECTOR#</span>\
                        </div>\
                        </div>\
                    </div>\
                </div>\
            </div>\
        </div>';
        var temp=header;
        for(var index in data.list)
        {
            if(index>23)
            {
                break;
            }
            temp=temp+row.replace(/#INAME#/g,data.list[index].chargeItemName)
            .replace(/#IMONEY#/g,data.list[index].price);
        }
        temp=temp+footer;

        temp=temp.replace(/#DATA#/g,data.curDate)//开票日期
                .replace(/#TRADETYPE#/g,data.industryCategory)//行业分类
                .replace(/#PNAME#/g,data.customerName)//姓名
                .replace(/#INDEX#/g,data.chargeCode)//序列号
                .replace(/#BALANCETYPE#/g,data.settlementTypeName)//结算类型
                .replace(/#TOTALCAP#/g,data.totalMoneyUpper)//合计大写
                .replace(/#TOTALLOW#/g,data.totalMoney)//合计小写
                .replace(/#SOCIALMONEY#/g,data.socialPubMoney)//社保金额
                .replace(/#PERSONALMONEY#/g,data.selfMoney)//个人金额
                .replace(/#DOCTOR#/g,data.doctorCode)//医生
                .replace(/#TOLLCOLLECTOR#/g,data.createCode);//收费员
        var pageInfo="";
        while(num>0)
        {
           pageInfo=pageInfo+temp;
           num--;
        }
        return pageInfo;
    }
})(jQuery);