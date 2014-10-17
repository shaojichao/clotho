var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: 'Task信息列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
                {header: 'MD5',  dataIndex: 'md5', width: 100,sortable:true },
		        {header: '文件名字', dataIndex: 'file_name', width: 250},
		        {header: '节点',  dataIndex: 'node_name', width: 100,sortable:true },
                {header: '服务器',  dataIndex: 'server_ip_wan', width: 150,sortable:true },
		        {header: '激活状态',  dataIndex: 'active_flag', width: 85,sortable:true,renderer:function(value){
			        	if(value=='1'){
			        		return "<span style='color: #3d90ff'>激活</span>";
			        	}else{
			        		return "<span style='color: #ff0000'>未激活";
			        	}
		        	}
		        },
		        {header: '开始时间',  dataIndex: 'begin_time', width: 160,sortable:true,renderer:function(value){
		        	if(value != null){
		        		return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
		        	}else{
		        		return '';
		        	}
	        	}
	        },
		        {header: '结束时间',  dataIndex: 'end_time', width: 160,sortable:true,renderer:function(value){
		        	if(value != null){
		        		var time = Ext.util.Format.date(new Date(value),'Y-m-d H:i:s')
		        		if(time=='1970-01-01 00:00:00'){
		        			time = "<span style='color: #ff0000'>未结束</span>";
		        		}
		        		return time;
		        	}else{
                        time = "<span style='color: #ff0000'>未结束</span>";
		        	}
	        	}}
		     ],
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'md5','file_name','node_id','server_id','server_ip_wan','node_name','active_flag','begin_time','end_time'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/cdn-management/task/list',
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
    })

});

//domReady
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel]
	});
});