var serverPop = Ext.create('Ext.window.Window', {
    id: 'serverWin',
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
            id: 'serverForm',
            width: 460,
            height: 200,
            url: path + '/cdn-management/server/saveServer',
            layout: 'anchor',
            defaults: {
                anchor: '80%',
                labelAlign: 'right',
                labelWidth: 80,
                blankText: '必填项'
            },
            // The fields
            defaultType: 'textfield',
            items: [{//隐藏域
                name: 'id',
                hidden: true
            },{
                fieldLabel: '外网IP',
                name: 'ip_wan',
                allowBlank: false,
                maxLength: 40,
                maxLengthText: '不能超过20个字符'
            },{
                fieldLabel: '内网IP',
                name: 'ip_lan',
                allowBlank: false,
                maxLength: 40,
                maxLengthText: '不能超过20个字符'
            },{
                fieldLabel: '是否激活',
                xtype: 'fieldcontainer',
                defaultType: 'radiofield',
                layout: 'hbox',
                items: [
                    {
                        boxLabel: '不激活',
                        name: 'active',
                        inputValue: '0'
                    }, {
                        boxLabel: '激活',
                        name: 'active',
                        inputValue: '1'
                    }
                ]
            },{
                xtype: 'combo',
                name: 'node_id',
                fieldLabel: '节点',
                allowBlank: false,
                store: Ext.create('Ext.data.JsonStore', {
                    storeId: 'nodeMenu',
                    autoLoad: true,
                    fields: ['id', 'name'],
                    proxy: {
                        type: 'ajax',
                        url: path + '/cdn-management/node/getAllNodes',
                        reader: {
                            totalProperty: 'results',
                            root: 'rows'
                        }
                    }
                }),
                displayField: 'name',
                valueField: 'id'
            }]
        })
    ],
    buttons: [
        {
            text: '保存',
            handler: function(){

                if(!Ext.getCmp('serverForm').getForm().isValid()){
                    return;
                }
                var ip_wan=Ext.getCmp('serverForm').getForm().findField('ip_wan').getValue();
                if(!isIP(ip_wan)){
                    alert('外网Ip不是一个IP地址，请您按照正确格式输入！');
                    return;
                }
                var ip_lan=Ext.getCmp('serverForm').getForm().findField('ip_lan').getValue();
                if(!isIP(ip_lan)){
                    alert('内网Ip不是一个IP地址，请您按照正确格式输入！');
                    return;
                }
                Ext.getCmp('serverForm').submit({
                    waitTitle: '系统提示',
                    waitMsg: '保存中......',
                    success: function(form, action){
                        Ext.getCmp('serverWin').hide();
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
                serverPop.hide();
            }
        }
    ]
});


var centerPanel = Ext.create('Ext.grid.Panel', {
    region: 'center',
    title: '服务器列表',
    columns: [
        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
        {header: '外网IP', dataIndex: 'ip_wan', width: 150,sortable:true },
        {header: '内网IP',  dataIndex: 'ip_lan', width: 150,sortable:true },

        {header: '激活状态',  dataIndex: 'active', width: 85,sortable:true,renderer:function(value){
            if(value=='1'){
                return "<span style='color: #3d90ff'>激活</span>";
            }else{
                return "<span style='color: #ff0000'>未激活</span>";
            }
        }
        },
        {header: '节点',  dataIndex: 'node_name', width: 160,sortable:true },
        {header: '节点描述',  dataIndex: 'node_desc', width: 160,sortable:true },
        {header: '创建时间',  dataIndex: 'create_time', width: 160,sortable:true,renderer:function(value){
            if(value != null){
                return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
            }else{
                return '';
            }
        }
        },
        {header: '修改时间',  dataIndex: 'update_time', width: 160,sortable:true,renderer:function(value){
            if(value != null){
                var time = Ext.util.Format.date(new Date(value),'Y-m-d H:i:s')
                if(time=='1970-01-01 00:00:00'){
                    time = '';
                }
                return time;
            }else{
                return '';
            }
        }}
    ],
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'centerStore',
        pageSize: 20,
        fields :['id', 'ip_wan','ip_lan','active','node_name','node_id','node_desc','create_time','update_time'],
        proxy: {
            type: 'ajax',
            url: path + '/cdn-management/server/list',
            reader: {
                type: 'json',
                totalProperty: "result",
                root: 'rows'
            }
        }
    }),
    tbar: [
        {
            xtype: 'button',
            text: '增加',
            handler: function(){
                Ext.getCmp('serverForm').getForm().reset();
                serverPop.setTitle('增加');
                serverPop.show();
            }
        },'-',{
            xtype: 'button',
            text: '编辑',
            handler: function(){

                var models = centerPanel.getSelectionModel().getSelection();
                if(models.length <= 0){
                    Ext.Msg.alert('系统提示', '请选择要编辑的数据');
                    return;
                }

                serverPop.setTitle('编辑');
                serverPop.show();

                var record = centerPanel.getStore().getById(models[0].get('id'));
                Ext.getCmp('serverForm').loadRecord(record);

            }
        },'-',{
            xtype: 'button',
            text: '删除',
            handler: function(){
                var models = centerPanel.getSelectionModel().getSelection();
                if(models.length <= 0){
                    Ext.Msg.alert('系统提示', '请选择要删除的数据');
                    return;
                }
                Ext.Msg.confirm('系统提示', '您确认要删除吗?', function(option){
                    if('yes' === option){
                        deleteServers(models);
                    }
                });
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
                serverPop.setTitle('编辑');
                serverPop.show();
                var record = centerPanel.getStore().getById(models[0].get('id'));
                Ext.getCmp('serverForm').loadRecord(record);
            }
        }
    }
});

function deleteServers(records){
    Ext.Ajax.request({
        url: path + '/cdn-management/server/deleteServer?id='+records[0].get('id'),
        scope: this,
        async: true,
        success: function(response, options){
            Ext.Msg.alert("系统提示", "删除成功");
            centerPanel.getStore().reload();
        },
        failure: function(form, action){
            Ext.Msg.alert('系统提示', action.result.msg);
        }
    });
}


function isIP(strIP) {
    var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g //匹配IP地址的正则表达式
    if(re.test(strIP))
    {
        if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;
    }
    return false;
}


//domReady
Ext.onReady(function(){
    Ext.create('Ext.container.Viewport', {
        layout: 'border',
        items:[centerPanel]
    });
});