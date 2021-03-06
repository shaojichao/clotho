var nodePop = Ext.create('Ext.window.Window', {
	id: 'nodeWin',
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
            	id: 'nodeForm',
            	width: 460,
            	height: 250,
                url: path + '/cdn-management/node/saveNode?adminName='+adminName,
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
            		        fieldLabel: '节点名称',
            		        name: 'name',
            		        allowBlank: false,
                            emptyText:'节点名称不能为空！',
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
            	    	   fieldLabel: '节点描述',
            	    	   name: 'desc',
            	    	   xtype: 'textarea',
            	    	   height: 100,
            	    	   allowBlank: false,
                           emptyText:'节点名称不能为空'
            	       }]
            })
    ],
    buttons: [
        {
        	text: '保存',
        	handler: function(){

        		if(!Ext.getCmp('nodeForm').getForm().isValid()){
        			return;
        		}

        		Ext.getCmp('nodeForm').submit({
        			waitTitle: '系统提示',
        			waitMsg: '保存中......',
        			success: function(form, action){
        				Ext.getCmp('nodeWin').hide();
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
                nodePop.hide();
        	}
        }
    ]
});


var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '节点列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
		        {header: '节点名称', dataIndex: 'name', width: 80},
		        {header: '节点描述',  dataIndex: 'desc', width: 300,sortable:true },
		        {header: '激活状态',  dataIndex: 'active', width: 85,sortable:true,renderer:function(value){
			        	if(value=='1'){
                            return "<span style='color: #3d90ff'>激活</span>";
			        	}else{
                            return "<span style='color: #ff0000'>未激活";
			        	}
		        	}
		        },
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
         selModel: {
         listeners: {
            select: function(rowModel, record, index, eOpts){
                serverPanel.getStore().load({params: {nodeid: record.data.id}});
            }
        },
        mode: 'MULTI'
    },
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'name','desc','active','create_time','update_time'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/cdn-management/node/list',
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
		       		Ext.getCmp('nodeForm').getForm().reset();
	   				nodePop.setTitle('增加');
		       		nodePop.show();
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

                    nodePop.setTitle('编辑');
                    nodePop.show();

                    var record = centerPanel.getStore().getById(models[0].get('id'));
                    Ext.getCmp('nodeForm').loadRecord(record);

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
	       					deleteNodes(models);
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
                nodePop.setTitle('编辑');
                nodePop.show();
                var record = centerPanel.getStore().getById(models[0].get('id'));
                Ext.getCmp('nodeForm').loadRecord(record);
       			}
        }
    }
});


var serverPanel = Ext.create('Ext.grid.Panel', {
    region: 'east',
    title: '服务器列表',
    width: 400,
    split: true,
    collapsible: true,
    columns: [
        {header: 'ID', align: 'center', width: 50, dataIndex: 'id'},
        {header: '外网IP', align: 'center', width: 120, dataIndex: 'ip_wan'},
        {header: '内网IP', align: 'center',width: 120, dataIndex: 'ip_lan'},
        {header: '激活状态',align: 'center',  dataIndex: 'active',renderer:function(value){
            if(value=='1'){
                return "<span style='color: #3d90ff'>激活</span>";
            }else{
                return "<span style='color: #ff0000'>未激活</span>";
            }
        }
        }
    ],
    store: Ext.create('Ext.data.JsonStore', {
        storeId:'eastStore',
        fields :['id','ip_wan','ip_lan','active'],
        proxy: {
            type: 'ajax',
            url: path + '/cdn-management/server/getServerListByNodeId',
            reader: {
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

function deleteNodes(records){
	Ext.Ajax.request({
		url: path + '/cdn-management/node/deleteNode?id='+records[0].get('id')+'&adminName='+adminName,
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


//domReady
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel,serverPanel]
	});
});