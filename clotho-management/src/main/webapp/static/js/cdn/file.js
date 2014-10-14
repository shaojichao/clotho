var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '文件信息列表',
    columns: [
		        {header: '文件',  dataIndex: 'fileName', width: 660,sortable:true },
		        {header: 'url', dataIndex: 'url', width: 500},
                {header: 'MediaType', dataIndex: 'mediaType', width: 100},
		        {header: 'VideoType', dataIndex: 'videoType', width: 100},
		        {header: 'Size',  dataIndex: 'size', width: 150,sortable:true }
		     ],
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['fileName', 'url','mediaType','videoType','size'],
	    proxy: {
	        type: 'ajax',
	        url: '/abc/cdn_management/file/list',
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