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
            url: path + '/abc/metis/upload.do',
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

                       //Ext.Msg.alert('系统成功提示', '保存成功！');
                        detailStatus.show();
                    },
                    failure: function(form, action){
                        Ext.getCmp('upgradeWin').hide();
                        Ext.getCmp('pkgUploadForm').getForm().reset();
                        centerPanel.getStore().load({url:'/abc/metis/schema.do'});

                        detailStatus.show();
                        statusPanel.getStore().loadData(action.result.rows);

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
var statusPanel=Ext.create('Ext.grid.Panel', {
    title: '处理结果',
    columns: [
        {text: 'namespace',  dataIndex:'namespace', width: 160},
        {text: 'name',  dataIndex:'name', width: 100},
        {header: '处理结果',  dataIndex: 'type', width: 270,sortable:true,renderer:function(value){
            if(value=='1'){
                return "fullname不存在，直接入库";
            }else if(value=='2'){
                return "schema校验通过，指纹相同，更新schema";
            }else if(value=='3'){
                return 'schema校验通过，指纹不相同,录入新指纹';
            }else if(value=='4'){
                return 'schema校验不通过，不做处理';
            }else{
                return 'unknown info';
            }
        }
        }

    ],
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'statusStore',
        pageSize: 20,
        fields:['namespace', 'name', 'type'],
        proxy: {
            type: 'ajax',
            url:'',
            reader: {
                type: 'json',
                totalProperty: "result",
                root: 'rows'            }

        }
    }),
    width: 530,
    forceFit: true,
    renderTo: Ext.getBody()
});

var detailStatus = Ext.create('Ext.window.Window',{
    id: 'detailStatus',
    title: '状态详细',
    height: 370,
    width: 580,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto' ,
    items : [statusPanel],
    buttons: [{
            text: '取消',
            handler: function(){
                detailStatus.hide();
            }
        }
    ]
});

var detailInfo = Ext.create('Ext.window.Window', {
    id: 'detailInfo',
    title: '详细信息',
    height: 400,
    width: 550,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto',
    items: [
        Ext.create('Ext.form.Panel', {
            id: 'detailInfoForm',
            width: 550,
            height: 400,
            url: path + '/menu/saveMenu.do',
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
                fieldLabel: 'fingerprint',
                name: 'fingerprint',
                allowBlank: false,
                maxLength: 100,
                maxLengthText: '不能超过20个字符'
            },{
                fieldLabel: 'namespace',
                name: 'namespace',
                allowBlank: false,
                maxLength: 100,
                maxLengthText: '不能超过20个字符'
            },{
                fieldLabel: 'name',
                name: 'name',
                allowBlank: false,
                maxLength: 100,
                maxLengthText: '不能超过20个字符'
            },
                {
                    xtype: 'textareafield',
                    fieldLabel: 'schema',
                    name: 'schema',
                    labelAlign: 'top',
                    height: 120,
                    margin: '0',
                    allowBlank: false
            },{
                fieldLabel: 'inuse',
                name: 'inuse',
                allowBlank: false,
                maxLength: 100,
                maxLengthText: '不能超过20个字符'
            },{
                fieldLabel: 'updatetime',
                name: 'updatetime',
                allowBlank: false,
                maxLength: 100,
                maxLengthText: '不能超过20个字符'
            },{
                fieldLabel: 'updateby',
                name: 'updateby',
                allowBlank: false,
                maxLength: 100,
                maxLengthText: '不能超过20个字符'
            },{
                fieldLabel: 'createtime',
                name: 'createtime',
                allowBlank: false,
                maxLength: 100,
                maxLengthText: '不能超过20个字符'
            }


            ]
        })
    ],
    buttons: [
      {
            text: '取消',
            handler: function(){
                detailInfo.hide();
            }
        }
    ]
});
//var statusStore = Ext.create('Ext.data.Store', {
//    fields: ['type', 'name'],
//    data : [
//        {"type":"1", "name":"使用中"},
//        {"type":"0", "name":"停用"}
//
//    ]
//});

var name_Fields=[
    {name:"name"}

];


var names_store = Ext.create('Ext.data.Store', {
    model: 'State',
    fields: ['name'],
    proxy: {
        type: 'ajax',
        url: '/abc/metis/names.do',
        reader: {
            totalProperty: 'result',
            root: 'rows'
        }
    },
    autoLoad: false,
    remoteSort:true
});

var centerPanel = Ext.create('Ext.grid.Panel', {
    region: 'center',
    title: 'schema列表',
    columns: [
        {header: 'fingerprint',  dataIndex: 'fingerprint', width: 230,sortable:true },
        {header: 'namespace', dataIndex: 'namespace', width: 180},
        {header: 'name', dataIndex: 'name', width: 100},
        {header: 'schema', dataIndex: 'schema', width: 80},
        {header: '状态', dataIndex: 'inuse', width: 80,sortable:true,renderer:function(value) {
            if (value == '0') {
                return "停用";
            } else if (value == '1') {
                return "使用中";
            }
        }
        },
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
            url:'/abc/metis/schema.do',
            reader: {
                type: 'json',
                totalProperty: "result",
                root: 'rows'            }

        }
    }),
    tbar: [

        {
            xtype: 'combo',
            id : 'namespace',
            name: 'namespace',
            fieldLabel: 'namespace',
            emptyText:'请选择...',
            store: Ext.create('Ext.data.JsonStore', {
                storeId: 'namespace',
                autoLoad: true,
                fields: ['namespace'],

                proxy: {
                    type: 'ajax',
                    url:'/abc/metis/namespaces.do',
                    reader: {
                        totalProperty: 'result',
                        root: 'rows'
                    }
                }
            }),
            listeners:
            {
                select : function(namespaceCombox, record,index)
                {

                    var namecom = Ext.getCmp("name");
                    namecom.store.load({params:{namespace:this.value}});
                    namecom.clearValue();
                }
            },
            displayField: 'namespace',
            valueField: 'namespace'
        },
        {
            xtype: 'combo',
            id : 'name',
            name: 'name',
            fieldLabel: 'name',
            emptyText:'请选择...',
            store: names_store,
            listeners:
           {
    select : function(nameCombox, record,index)
    {

        var namespacecom = Ext.getCmp("namespace");

        centerPanel.getStore().load({url:'/abc/metis//samplename.do',params:{name:this.value,namespace:namespacecom.value}});
        centerPanel.clearValue();
    }
     },
            displayField: 'name',
            valueField: 'name'
        },
        {
            xtype: 'button',
            text: '上传',
            handler: function () {
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

                detailInfo.setTitle('详细信息');
                detailInfo.show();

                console.log(models[0].data);
                Ext.getCmp('detailInfoForm').loadRecord(models[0]);

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