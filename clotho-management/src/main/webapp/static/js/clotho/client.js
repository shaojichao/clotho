var clientPop = Ext.create('Ext.window.Window', {
	id: 'clientWin',
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
            	id: 'clientForm',
            	width: 460,
            	height: 250,
                url: path + '/clotho/client/saveClient.do',
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
                			name: 'clientId',
                			hidden: true
                		},{
            		        fieldLabel: '名称',
            		        name: 'name',
            		        allowBlank: false,
            		        maxLength: 40,
            		        maxLengthText: '不能超过20个字符'
                		},{
                			fieldLabel: '描述',
             	    	    name: 'description',
             	    	    xtype: 'textarea',
             	    	    height: 100,
             	    	    maxLength: 40,
             	    	    allowBlank: false
                		}]
            })
    ],
    buttons: [
        {
        	text: '保存',
        	handler: function(){
        		
        		if(!Ext.getCmp('clientForm').getForm().isValid()){
        			return;
        		}
        		
        		Ext.getCmp('clientForm').submit({
        			waitTitle: '系统提示',
        			waitMsg: '保存中......',
        			success: function(form, action){
        				Ext.getCmp('clientWin').hide();
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
        		upgradePop.hide();
        	}
        }
    ]
});


var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '菜单列表',
    columns: [
		        {header: 'clientId',  dataIndex: 'clientId', width: 60,sortable:true },
		        {header: '名称', dataIndex: 'name', width: 100},
		        {header: '描述', dataIndex: 'description', width: 200},
		        {header: '创建人',  dataIndex: 'createby', width: 80,sortable:true },
		        {header: '创建时间',  dataIndex: 'createtime', width: 160,sortable:true,renderer:function(value){
			        	if(value != null){
			        		return Ext.util.Format.date(new Date(value),'Y-m-d H:i:s');
			        	}else{
			        		return '';
			        	}
		        	}
		        },
		        {header: '修改人',  dataIndex: 'updateby', width: 80,sortable:true },
		        {header: '修改时间',  dataIndex: 'updatetime', width: 160,sortable:true,renderer:function(value){
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
	    fields :['clientId', 'name','description','createby','createtime','updateby','updatetime'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/clotho/client/allList.do',
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
		       		Ext.getCmp('clientForm').getForm().reset();
		       		clientPop.setTitle('增加');
		       		clientPop.show();
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
	       			clientPop.setTitle('编辑');
	       			clientPop.show();
	       			
	       			Ext.getCmp('clientForm').loadRecord(models[0]);
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
       			clientPop.setTitle('编辑');
       			clientPop.show();
       			
       			Ext.getCmp('clientForm').loadRecord(models[0]);
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