var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: 'Job列表',
    height:500,
    columns: [
        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
        {header: 'MD5', dataIndex: 'md5', width: 150},
        {header: '文件URL',  dataIndex: 'url', width: 150,sortable:true },
        {header: '文件名',  dataIndex: 'file_name', width: 150,sortable:true },
        {header: '分发策略', dataIndex: 'strategy',width: 150,sortable:true,renderer:function(value){
            if(value=='1'){
                return "<span style='color: #3d90ff'>冷文件</span>";
            }else if(value=='2'){
                return "<span style='color: #34ff8f'>普通文件";
            }else if(value=='3')
            {
                return "<span style='color: #ff0000'>热文件</span>";
            }
        }
        },
        {header: '回调Url',  dataIndex: 'back_url', width: 150,sortable:true },
        {header: '进度', dataIndex: 'process',width: 150,sortable:true,renderer:function(value){
            if(value=='100'){
                return "<span style='color: #32ff29'>完成</span>";
            }else
            {
                return "<span style='color: #ff0000'>未完成</span>";
            }
        }
        },
        {header: '任务状态', dataIndex: 'status',width: 150,sortable:true,renderer:function(value){
            if(value=='0'){
                return "<span style='color: #32ff29'>完成</span>";
            }else if(value=='1'){
                return "<span style='color: #2b25ff'>正下载</span>";
            }else if(value=='2'){
                return "<span style='color: #de78ff'>计划 </span>";
            }
            else if(value=='3'){
                return "<span style='color: #3629ff'>分发  </span>";
            }else
            {
                return "<span style='color: #ff0000'>失败</span>";
            }
        }
        },
        {header: '说明',  dataIndex: 'memo', width: 80,sortable:true },
        {header: 'taskId',  dataIndex: 'task_id', width: 50,sortable:true },
        {header: 'mediaType',  dataIndex: 'media_type', width: 150,sortable:true },
        {header: 'mediaDataRate',  dataIndex: 'media_data_rate', width: 120,sortable:true },
        {header: 'videoType',  dataIndex: 'video_type', width: 150,sortable:true },
        {header: 'videoDataRate',  dataIndex: 'video_data_rate', width: 120,sortable:true },
        {header: 'audioType',  dataIndex: 'audio_type', width: 150,sortable:true },
        {header: 'audioDataRate',  dataIndex: 'audio_data_rate', width: 120,sortable:true },
        {header: 'duration',  dataIndex: 'duration', width: 150,sortable:true },
        {header: 'size',  dataIndex: 'size', width: 100,sortable:true },
        {header: '创建时间',  dataIndex: 'begin_time', width: 160,sortable:true,renderer:function(value){
            if(value != null){
                return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
            }else{
                return '';
            }
        }
        },
        {header: '修改时间',  dataIndex: 'end_time', width: 160,sortable:true,renderer:function(value){
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
    selModel: {
        listeners: {
            select: function(rowModel, record, index, eOpts){
                taskPanel.getStore().load({params: {jobid: record.data.id}});
            }
        },
        mode: 'MULTI'
    },
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'centerStore',
        pageSize: 20,
        fields :['id', 'md5','url','file_name','strategy','back_url','process','status','memo','task_id','media_type','media_data_rate','video_type','video_data_rate','audio_type','audio_data_rate','duration','size','storage_time','begin_time','end_time'],
        proxy: {
            type: 'ajax',
            url: path + '/abc/job/list',
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

var taskPanel = Ext.create('Ext.grid.Panel', {
	region: 'east',
	title: 'Task列表',
	width: 350,
	split: true,
	collapsible: true,
	columns: [
	          	{header: 'ID', align: 'center', width: 50, dataIndex: 'id'},
	          	{header: '节点', align: 'center', width: 50, dataIndex: 'node_name'},
                 {header: '服务器', align: 'center', width: 150, dataIndex: 'server_ip_wan'},
                {header: '激活状态', align: 'center', width: 100, dataIndex: 'active_flag', renderer: function (value) {
                    if (value == '1') {
                        return "<span style='color: #3d90ff'>激活</span>";
                    } else {
                        return "<span style='color: #ff0000'>未激活</span>";
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
		        			time = '';
		        		}
		        		return time;
		        	}else{
		        		return '';
		        	}
	        	}}
		     ],
                selModel: {
                listeners: {
                    select: function(rowModel, record, index, eOpts){
                        taskLogPanel.getStore().load({params: {taskid: record.data.id}});
                    }

                },
                mode: 'MULTI'
            },



	store: Ext.create('Ext.data.JsonStore', {
	    storeId:'eastStore',
        pageSize: 20,
	    fields :['id','node_id','node_name','server_ip_wan','server_id','active_flag','begin_time','end_time'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/abc/task/getTaskListByJobId',
	        reader: {
                type: 'json',
	        	totalProperty: 'results',
	            root: 'rows'
	        }
	    }
	}),

	bbar: Ext.create('Ext.toolbar.Paging', {
        store: Ext.data.StoreManager.get('eastStore'),
        displayInfo: true,
        displayMsg: '第{0}-{1}条，共{2}条',
        emptyMsg: "没有数据",
        beforePageText: '第',
        afterPageText: '页，共 {0} 页'
    })
});


var taskLogPanel = Ext.create('Ext.grid.Panel', {
    region: 'south',
    height:250,
    title: 'TaskLog列表',
    split: true,
    collapsible: true,
    columns: [
        {header: 'ID', align: 'center', width: 50, dataIndex: 'id'},
        {header: '状态', align: 'center', width: 150, dataIndex: 'status'},
        {header: '备注', align: 'center', width: 150, dataIndex: 'memo'},

        {header: '日志时间',  dataIndex: 'log_time', width: 160,sortable:true,renderer:function(value){
            if(value != null){
                return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
            }else{
                return '';
            }
        }
        }
    ],
    store: Ext.create('Ext.data.JsonStore', {
        storeId:'southStore',
        pageSize: 20,
        fields :['id','status','memo','log_time'],
        proxy: {
            type: 'ajax',
            url: path + '/abc/taskLog/getTaskLogsListByTaskId',
            reader: {
                totalProperty: 'results',
                root: 'rows'
            }
        }
    }),
    bbar: Ext.create('Ext.toolbar.Paging', {
        store: Ext.data.StoreManager.get('southStore'),
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
		items:[centerPanel,taskPanel,taskLogPanel]
	});
});