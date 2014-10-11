var upgradePop = Ext.create('Ext.window.Window', {
    id: 'upgradeWin',
    title: '增加',
    height: 370,
    width: 480,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto',
    items: [
        Ext.create('Ext.form.Panel', {
            id: 'upgradeForm',
            width: 460,
            height: 100,
            url: path + '/metis/upload.do',
            layout: 'anchor',
            defaults: {
                anchor: '80%',
                labelAlign: 'right',
                labelWidth: 80,
                blankText: '必填项'
            },
            // The fields
            defaultType: 'textfield',
            items: [{
                fieldLabel: '文件地址',
                name: 'path',
                id: 'path',
                allowBlank: false
            }]
        }),
        //覆盖升级包的选择上传功能
        Ext.create('Ext.form.Panel', {
            url: path + '/upload/upload.do',
            width: 560,
            height: 27,
            layout: 'hbox',
            id: 'pkgUploadForm',
            items: [
                {
                    fieldLabel: '选择文件',
                    labelWidth: 80,
                    labelAlign: 'right',
                    xtype: 'textfield',
                    width: 320,
                    margin: '0 5 0 0',
                    id: 'pkgText',
                    readOnly: true
                },{
                    xtype: 'filefield',
                    buttonText: '浏览',
                    name: 'pkg',
                    buttonOnly: true,
                    listeners: {
                        change: function(fileField, string, eOpts){
                            Ext.getCmp('pkgText').setValue(string);
                            Ext.getCmp('pkgUploadBtn').setDisabled(false);
                        }
                    }
                },{
                    xtype: 'button',
                    id: 'pkgUploadBtn',
                    text: '上传',
                    disabled: true,
                    margin: '0 0 0 10',
                    handler: function(){
                        Ext.getCmp('pkgUploadForm').getForm().submit({
                            waitTitle: '系统提示',
                            waitMsg: 'schema文件上传中，请稍候......',
                            success: function(form, action){
                                Ext.getCmp('pkgUploadBtn').setDisabled(true);
                                Ext.getCmp('path').setValue(action.result.msg);
                                Ext.Msg.alert('系统提示', 'schema文件上传成功！');
                            }
                        });
                    }
                }
            ]
        })
    ],
    buttons: [
        {
            text: '保存',
            handler: function(){

                if(!Ext.getCmp('upgradeForm').getForm().isValid()){
                    return;
                }

                Ext.getCmp('upgradeForm').submit({
                    waitTitle: '系统提示',
                    waitMsg: '保存中......',
                    success: function(form, action){

                        Ext.getCmp('upgradeWin').hide();
                        Ext.getCmp('pkgUploadForm').getForm().reset();
                        centerPanel.getStore().reload();
                        Ext.Msg.alert('系统提示', '保存成功！');
                    },
                    failure: function(form, action){
                        Ext.Msg.alert('系统提示', action.result.msg);
                    }
                });
            }
        },{
            text: '取消',
            handler: function(){
                upgradePop.hide();
            }
        }
    ]
});




var centerPanel = Ext.create('Ext.grid.Panel', {
    region: 'center',
    title: 'schema列表',
    columns: [
        {header: '主键',  dataIndex: 'fingerprint', width: 230,sortable:true },
        {header: 'namespace', dataIndex: 'namespace', width: 180},
        {header: 'name', dataIndex: 'name', width: 100},
        {header: 'schema', dataIndex: 'schema', width: 80},
        {header: '是否可用', dataIndex: 'inuse', width: 80},
        {header: '更新时间', dataIndex: 'updatetime', width: 180},
        {header: '更新者', dataIndex: 'updateby', width: 80},
        {header: '创建时间', dataIndex: 'createtime', width: 180}
    ],

    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'centerStore',
        pageSize: 20,
        fields :['fingerprint', 'namespace','name','schema','inuse','updatetime','updateby','createtime'],
        proxy: {
            type: 'ajax',
            url:'/metis/schema.do',
            reader: {
                type: 'json',
                totalProperty: "result",
                root: 'rows'            }

        }
    }),
    tbar: [
        {
            xtype: 'button',
            text: '上传',
            handler: function(){
                Ext.getCmp('upgradeForm').getForm().reset();
                Ext.getCmp('pkgUploadForm').getForm().reset();
                upgradePop.setTitle('增加');
                upgradePop.show();
            }
        }
    ],
    bbar: Ext.create('Ext.toolbar.Paging', {//xtype: pagingtoolbar
        store: Ext.data.StoreManager.get('centerStore'),
        displayInfo: true,
        displayMsg: '第{0}-{1}条，共{2}条',
        emptyMsg: "没有数据",
        beforePageText: '第',
        afterPageText: '页，共 {0} 页'
    }),
    listeners: {
        dblclick: {
            element: 'body', //bind to the underlying body property on the panel
            fn: function(){
                var models = centerPanel.getSelectionModel().getSelection();
                if(models.length <= 0){
                    Ext.Msg.alert('系统提示', '请选择要编辑的数据');
                    return;
                }

                upgradePop.setTitle('编辑');
                upgradePop.show();

                console.log(models[0].data);
                Ext.getCmp('upgradeForm').loadRecord(models[0]);
                if(models[0].data.pkgurl){
                    Ext.getCmp('pkgurl').setValue(models[0].data.pkgurl);
                }
            }
        }
    }
});
//domReady
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel]
	});
});