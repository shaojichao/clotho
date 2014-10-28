var centerPanel = Ext.create('Ext.grid.Panel', {
    region: 'center',
    title: '服务器文件列表',
    columns: [
        {header: 'ID', dataIndex: 'id', width: 60, sortable: true },
        {header: '文件', dataIndex: 'fileName', width: 500},
        {header: '节点', dataIndex: 'nodeName', width: 60},
        {header: '节点描述', dataIndex: 'nodeDesc', width: 150},
        {header: '服务器IP（外网）', dataIndex: 'ipWan', width: 150},
        {header: '服务器IP（内网）', dataIndex: 'ipLan', width: 150},
        {header: '激活状态', dataIndex: 'active', width: 85, sortable: true, renderer: function (value) {
            if (value == 1) {
                return "<span  style='color:green'>激活</span>";
            } else if (value == 0) {
                return "<span style ='color:red'>未激活</span>";
            } else {
                return "其他";
            }
        }
        }
    ],
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'centerStore',
        pageSize: 20,
        fields: ['id', 'nodeName', 'nodeDesc', 'ipWan', 'ipLan', 'fileName', 'active'],
        proxy: {
            type: 'ajax',
            url: path + '/cdn-management/serverFile/list',
            reader: {
                type: 'json',
                totalProperty: "result",
                root: 'rows'
            }
        }
    }),
    tbar: [
        {
            xtype: 'combo',
            id: 'fileName',
            name: 'fileName',
            typeAhead: false,
            hideTrigger: true,
            fieldLabel: '文件名',
            labelWidth:50,
            width:200,
            emptyText: '支持明文，密文查询',
            displayField: 'fileName',
            valueField: 'fileName'
        },
        {
            xtype: 'button',
            text: '查询',
            handler: function () {
                var fileName = Ext.getCmp('fileName').getValue();
                centerPanel.store.load({url: path + '/cdn-management/serverFile/search?fileName=' + fileName});
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
    }
});

//domReady
Ext.onReady(function () {
    Ext.create('Ext.container.Viewport', {
        layout: 'border',
        items: [centerPanel]
    });
});