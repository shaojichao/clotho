var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: 'LOG列表',
    columns: [
		        {header: 'id',  dataIndex: 'id', width: 60,sortable:true },
		        {header: '操作类型', dataIndex: 'opType', width: 100},
		        {header: '系统', dataIndex: 'systemId', width: 100},
		        {header: '模块', dataIndex: 'opMod', width: 100},
		        {header: '内容', dataIndex: 'content', width: 300},
		        {header: '创建人',  dataIndex: 'createby', width: 80,sortable:true },
		        {header: '创建时间',  dataIndex: 'createtime', width: 160,sortable:true,renderer:function(value){
			        	if(value != null){
			        		return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
			        	}else{
			        		return '';
			        	}
		        	}
		        }
		     ],
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'content','opType','opMod','createtime','createby','systemId'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/clotho/oplog/list.do',
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
	       		text: '查看',
	       		handler: function(){
	       			var models = centerPanel.getSelectionModel().getSelection();
	       			if(models.length <= 0){
	       				Ext.Msg.alert('系统提示', "请选择数据");
	       			}else{
	       				Ext.Msg.alert('系统提示', models[0].data.content);
	       			}
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
            element: 'body',
            fn: function(){ 
            	var models = centerPanel.getSelectionModel().getSelection();
       			Ext.Msg.alert('系统提示', models[0].data.content);
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