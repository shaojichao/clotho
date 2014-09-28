var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '升级包列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
		        {header: '版本号', dataIndex: 'version', width: 80},
		        {header: '时间序列号', dataIndex: 'serialno', width: 120},
		        {header: '版本描述',  dataIndex: 'memo', width: 300,sortable:true },
		        {header: '下载地址',  dataIndex: 'pkgurl', width: 300,sortable:true },
		        {header: '客户端类型',  dataIndex: 'clientid', width: 85,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "Android";
			        	}else if(value=='2'){
			        		return "IOS";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '是否提示',  dataIndex: 'showtype', width: 80,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "不提示";
			        	}else if(value=='2'){
			        		return "提示";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '升级类型',  dataIndex: 'upgradetype', width: 80,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "可选升级";
			        	}else if(value=='2'){
			        		return "强制升级";
			        	}else{
			        		return value;
			        	}
		        	},
		        },
		        {header: '创建人',  dataIndex: 'createby', width: 80,sortable:true },
		        {header: '创建时间',  dataIndex: 'createtime', width: 160,sortable:true},
		        {header: '修改人',  dataIndex: 'updateby', width: 80,sortable:true },
		        {header: '修改时间',  dataIndex: 'updatetime', width: 160,sortable:true }
		     ],
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'version','serialno','memo','pkgurl','clientid','showtype','upgradetype',
	             'createby','createtime','updateby','updatetime'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/clotho/upgrade/list.do',
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
    	dblclick: {
            element: 'body', //bind to the underlying body property on the panel
            fn: function(){ 
            	var models = centerPanel.getSelectionModel().getSelection();
        		Ext.Msg.alert('反馈内容', models[0].get('content'));
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