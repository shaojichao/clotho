var demoPop = Ext.create('Ext.window.Window', {
	id: 'demoWin',
    title: '增加',
    height: 500,
    width: 480,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto',
    items: [
            Ext.create('Ext.form.Panel', {
            	id: 'demoForm',
            	width: 460,
            	height: 370,
            	// 将会通过 AJAX 请求提交到此URL
                url: "",
                // 表单域 Fields 将被竖直排列, 占满整个宽度
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
                			id:'id',
                			hidden: true
                		},{
            		        fieldLabel: '名称',
            		        name: 'text',
            		        id:'text',
            		        allowBlank: false,
            		        maxLength: 40,
            		        maxLengthText: '不能超过40个字符'
                		},{
            		        fieldLabel: 'URL',
            		        name: 'url',
            		        id:'url',
            		        allowBlank: false,
            		        maxLength: 40,
            		        maxLengthText: '不能超过40个字符'
                		},{
                			xtype: 'combo',
							name: 'status',
							fieldLabel: '状态',
							store: Ext.create('Ext.data.ArrayStore', {
				       			fields: ['value', 'text'],
				       			data: [['ACTIVE','有效'],['INACTIVE','无效']]
					   		}),
					   		value: '',
					   		valueField: 'value'
                		}]
            })
    ],
    buttons: [
        {
        	text: '保存',
        	handler: function(){
        		
        		if(!Ext.getCmp('demoForm').getForm().isValid()){
        			return;
        		}
        		
        		Ext.getCmp('demoForm').submit({
        			waitTitle: '系统提示',
        			waitMsg: '保存中......',
        			success: function(form, action){
        				Ext.getCmp('demoWin').hide();
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
        		demoPop.hide();
        	}
        }
    ]
});

var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 60,sortable:true },
		        {header: '名称', dataIndex: 'text', width: 100},
		        {header: 'URL', dataIndex: 'url', width: 300},
		        {header: '状态', dataIndex: 'status', width: 200,renderer:function(value){
		        	if(value=='ACTIVE'){
		        		return "有效";
		        	}else{
		        		return "无效";
		        	}
		        }}
		     ],
	selModel: {
		listeners: {
			select: function(rowModel, record, index, eOpts){
//				eastPanel.getStore().load({params: {upgradeId: record.data.id}});
			}
		},
		mode: 'MULTI'
	},
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
		pageSize: 20,
	    fields :['id', 'text','url','status'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/demo/list.do',
	        reader: {
	            type: 'json',
	            totalProperty: "results",
	            root: 'rows'
	        }
	    }
	}),
	tbar: [
	       	{
	       		xtype: 'button',
	       		text: '增加',
	       		handler: function(){
    				demoPop.setTitle('增加');
    				demoPop.show();
	       		}
	       	},'-',{
	       		xtype: 'button',
	       		text: '编辑',
	       		handler: function(){
	       			var models = centerPanel.getSelectionModel().getSelection();
	       			Ext.getCmp('demoForm').loadRecord(models[0]);
	       			demoPop.setTitle('编辑');
	       			demoPop.show();
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
    })
});

//domReady
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel]
	});
});