$.extend({

    // 项目名称
    PROJECT_NAME: '/',
    // 默认显示的后台管理界面
    defaultUrl: null,

    /**
     * 打开新的Tab页
     *
     * @param id Tab页ID
     * @param dataUrl Tab页链接
     * @param menuName Tab页标题
     */
    openNewTab: function (id, dataUrl, menuName) {
        var flag = true;
        if (dataUrl == undefined || $.trim(dataUrl).length == 0)return false;
        // 选项卡菜单已存在
        $('.J_menuTab', window.parent.document).each(function () {
            if ($(this).data('id') == dataUrl) {
                if (!$(this).hasClass('active')) {
                    $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                    //scrollToTab(this);
                    // 显示tab对应的内容区
                    $('.J_mainContent .J_iframe', window.parent.document).each(function () {
                        if ($(this).data('id') == dataUrl) {
                            $(this).show().siblings('.J_iframe').hide();
                            return false;
                        }
                    });
                }
                flag = false;
                return false;
            }
        });

        // 选项卡菜单不存在
        if (flag) {
            var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
            $('.J_menuTab', window.parent.document).removeClass('active');

            // 添加选项卡对应的iframe
            var str1 = '<iframe class="J_iframe" name="iframe' + id + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
            $('.J_mainContent', window.parent.document).find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);

            //显示loading提示
//            var loading = layer.load();
//
//            $('.J_mainContent iframe:visible').load(function () {
//                //iframe加载完成后隐藏loading提示
//                layer.close(loading);
//            });
            // 添加选项卡
            $('.J_menuTabs .page-tabs-content', window.parent.document).append(str);
            //scrollToTab($('.J_menuTab.active', window.parent.document));
        }
        return false;
    },

    showLoading: function (element) {
        var loadingHtml = '<div class="spiner-example"><div class="sk-spinner sk-spinner-wave"><div class="sk-rect1"></div><div class="sk-rect2"></div><div class="sk-rect3"></div><div class="sk-rect4"></div><div class="sk-rect5"></div></div></div>';
        $(element).html(loadingHtml);
    },
    
    callBackParent: function (parentDataUrl, closeCurrent, callBack, swichParent) {
        //扩展swichParent
        //说明closeCurrent是否关闭当前tab，swichParent是否切换到父tab页
        if(swichParent==undefined)  swichParent = true;
        var currentTabId;
        $('.J_menuTab', window.parent.document).each(function () {
            if ($(this).hasClass('active')) {
                currentTabId = $(this).data('id');
                //  移除当前选项卡
                if (closeCurrent) {
                    $(this).remove();
                }
            }
        });
        $('.J_menuTab', window.parent.document).each(function () {
            if ($(this).data('id') == parentDataUrl) {
                if (!$(this).hasClass('active')) {
                    if(swichParent)   $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                    //scrollToTab(this);
                    // 显示tab对应的内容区
                    $('.J_mainContent .J_iframe', window.parent.document).each(function () {
                        if ($(this).data('id') == parentDataUrl) {
                            callBack(this.contentWindow);
                            if(swichParent)    $(this).show().siblings('.J_iframe').hide();
                        }
                    });
                }
            }
        });
        // 移除相应tab对应的内容区
        if (closeCurrent) {
            $('.J_mainContent', window.parent.document).find('.J_iframe').each(function () {
                if ($(this).data('id') == currentTabId) {
                    $(this).remove();
                    return;
                }
            });
        }
    }
});