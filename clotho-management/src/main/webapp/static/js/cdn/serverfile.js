var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '服务器文件列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
		        {header: '服务器IP（外网）', dataIndex: 'ipWan', width: 150},
                {header: '服务器IP（内网）', dataIndex: 'ipLan', width: 150},
		        {header: '文件', dataIndex: 'fileName', width: 700},
		        {header: '激活状态',  dataIndex: 'active', width: 100,sortable:true }
		     ],
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'ipWan','ipLan','fileName','active'],
	    proxy: {
	        type: 'ajax',
	        url: '/abc/cdn_management/serverFile/list',
	        reader: {
	            type: 'json',
	            totalProperty: "result",
	            root: 'rows'
	        }
	    }
	}),
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
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel]
	});
});