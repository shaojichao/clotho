var _roleid;
var rolePop = Ext.create('Ext.window.Window', {
	id: 'roleWin',
    title: '增加',
    height: 200,
    width: 300,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto',
    items: [
            Ext.create('Ext.form.Panel', {
            	id: 'roleForm',
                url: path + '/admin/saveRole.do',
                layout: 'anchor',
                defaults: {
                    anchor: '80%',
                    labelAlign: 'right',
                    labelWidth: 50,
                    blankText: '必填项'
                },
                // The fields
                defaultType: 'textfield',
                items: [{//隐藏域
		        			name: 'id',
		        			hidden: true
		        		},{
            		        fieldLabel: '名称',
            		        name: 'name',
            		        allowBlank: false
                		},{
            		        fieldLabel: '描述',
            		        name: 'description',
            		        allowBlank: false
                		},{
                			xtype: 'combo',
							name: 'status',
							fieldLabel: '状态',
							store: Ext.create('Ext.data.ArrayStore', {
				       			fields: ['value', 'text'],
				       			data: [['ACTIVE','有效'],['INACTIVE','无效']]
					   		}),
					   		displayField: 'text',
					   	    valueField: 'value',
                		}]
            })
    ],
    buttons: [
        {
        	text: '保存',
        	handler: function(){
        		
        		if(!Ext.getCmp('roleForm').getForm().isValid()){
        			return;
        		}
        		
        		Ext.getCmp('roleForm').submit({
        			waitTitle: '系统提示',
        			waitMsg: '保存中......',
        			success: function(form, action){
        				Ext.getCmp('roleWin').hide();
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
        		rolePop.hide();
        	}
        }
    ]
});

var centerPanel = Ext.create('Ext.grid.Panel', {
	region: 'center',
	title: '账号列表',
    columns: [
		        {header: 'ID',  dataIndex: 'id', width: 80,sortable:true },
		        {header: '名称', dataIndex: 'name',width:100},
		        {header: '描述', dataIndex: 'description',width:100},
		        {header: '状态', dataIndex: 'status',width:100,renderer:function(value){
		        	if(value=='ACTIVE'){
		        		return "有效";
		        	}else{
		        		return 无效;
		        	}
	        	},
	        }
 		     ],
	selModel: {
	listeners: {
			select: function(rowModel, record, index, eOpts){
//				eastPanel.getStore().load({params: {roleid: record.data.id}});
			}
		},	
		mode: 'MULTI'
	},
	store: Ext.create('Ext.data.JsonStore', {
		autoLoad: true,
		storeId: 'centerStore',
	    fields :['id', 'name','description','status'],
	    proxy: {
	        type: 'ajax',
	        url: path + '/admin/roles.do',
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
		       		Ext.getCmp('roleForm').getForm().reset();
	   				rolePop.setTitle('增加');
	   				rolePop.show();
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
	       			rolePop.setTitle('编辑');
	       			rolePop.show();
	       			Ext.getCmp('roleForm').loadRecord(models[0]);
	       		}
	       	}
	      ]
});


//domReady
Ext.onReady(function(){
	Ext.create('Ext.container.Viewport', {
		layout: 'border',
		items:[centerPanel]
	});
});