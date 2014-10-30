var userPop = Ext.create('Ext.window.Window', {
    id: 'userWin',
    title: '增加',
    height: 620,
    width: 650,
    bodyPadding: 5,
    maximizable: true,
    modal: true,
    closeAction: 'hide',
    overflowX: 'hidden',
    overflowY: 'auto',
    items: [
        Ext.create('Ext.form.Panel', {
            id: 'userForm',
            width: 600,
            height: 400,
            url: path + '/abc/uc_management/usermanage/saveUser.do',
            method: 'POST',
            layout: 'anchor',
            defaults: {
                anchor: '80%',
                labelAlign: 'right',
                labelWidth: 80,
                blankText: '必填项'
            },
            // The fields
            defaultType: 'textfield',
            items: [
                {//隐藏域
                    name: 'id',
                    hidden: true
                },
                {
                    fieldLabel: '用户名',
                    name: 'userid',
                    allowBlank: false
                },
                {
                    fieldLabel: '用户类型',
                    name: 'useridtype',
                    xtype: 'combo',
                    store: Ext.create('Ext.data.ArrayStore', {
                        fields: ['value', 'text'],
                        data: [
                            [1, '邮箱'],
                            [2, '手机']
                        ]
                    }),
                    displayField: 'text',
                    valueField: 'value'
                },
                {
                    fieldLabel: '姓名',
                    name: 'name'
                },
                {
                    fieldLabel: '昵称',
                    name: 'nickname'
                },
                {
                    fieldLabel: '年龄',
                    name: 'age'
                },
                {
                    fieldLabel: '性别',
                    name: 'gender',
                    xtype: 'combo',
                    store: Ext.create('Ext.data.ArrayStore', {
                        fields: ['value', 'text'],
                        data: [
                            [0, '女'],
                            [1, '男']
                        ]
                    }),
                    displayField: 'text',
                    valueField: 'value'
                },
                {
                    fieldLabel: '手机号',
                    name: 'mobile'
                },
                {
                    fieldLabel: '邮箱',
                    name: 'mail'
                },
                {
                    fieldLabel: '职业',
                    name: 'occupation'
                },
                {
                    fieldLabel: '地址',
                    name: 'address'
                },
                {
                    fieldLabel: '会员等级',
                    xtype: 'numberfield',
                    name: 'viplevel'
                },
                {
                    fieldLabel: '所在区域',
                    name: 'location',
                    xtype: 'combo',
                    store: Ext.create('Ext.data.ArrayStore', {
                        fields: ['value', 'text'],
                        data: [
                            ['CN', '中国'],
                            ['US', '美国']
                        ]
                    }),
                    displayField: 'text',
                    valueField: 'value'
                },
                {
                    name: 'updateby',
                    hidden: true
                }
            ]
        })
    ],
    buttons: [
        {
            text: '保存',
            handler: function () {

                if (!Ext.getCmp('userForm').getForm().isValid()) {
                    return;
                }
                Ext.getCmp('userForm').getForm().findField('updateby').setValue(adminName);
                Ext.getCmp('userForm').submit({
                    waitTitle: '系统提示',
                    waitMsg: '保存中......',
                    success: function (form, action) {
                        Ext.getCmp('userWin').hide();
                        centerPanel.getStore().reload();
                        Ext.Msg.alert('系统提示', '保存成功！');
                    },
                    failure: function (form, action) {
                        Ext.Msg.alert('系统提示', action.result.msg);
                    }
                });
            }
        },
        {
            text: '取消',
            handler: function () {
                userPop.hide();
            }
        }
    ]
});


var centerPanel = Ext.create('Ext.grid.Panel', {
    region: 'center',
    title: '用户列表',
    columns: [
        {header: '用户序号', dataIndex: 'id', width: 80, sortable: true },
        {header: '用户名', dataIndex: 'userid', width: 200},
        {header: '用户类型', dataIndex: 'useridtype', width: 80, renderer: function (value) {
            if (value == '1') {
                return "邮箱";
            } else if (value == '2') {
                return "手机";
            }  else {
                return value;
            }
        }},
        {header: '姓名', dataIndex: 'name', width: 100},
        {header: '昵称', dataIndex: 'nickname', width: 100},
        {header: '年龄', dataIndex: 'age', width: 80},
        {header: '性别', dataIndex: 'gender', width: 80, renderer: function (value) {
            if (value == '0') {
                return "女";
            } else if (value == '1') {
                return "男";
            }  else {
                return value;
            }
        }},
        {header: '手机号', dataIndex: 'mobile', width: 120},
        {header: '邮箱', dataIndex: 'mail', width: 200},
        {header: '职业', dataIndex: 'occupation', width: 100},
        {header: '地址', dataIndex: 'address', width: 200},
        {header: '会员等级', dataIndex: 'viplevel', width: 80},
        {header: '所在区域', dataIndex: 'location', width: 80, sortable: true, renderer: function (value) {
            if (value == 'CN') {
                return "中国";
            } else if (value == 'US') {
                return "美国";
            } else {
                return value;
            }
        }},
        {header: '修改人', dataIndex: 'updateby', width: 120, sortable: true },
        {header: '修改时间', dataIndex: 'updatetime', width: 160, sortable: true, renderer: function (value) {
            if (value != null) {
                return Ext.util.Format.date(new Date(value), 'Y-m-d H:i:s');
            } else {
                return '';
            }
        }
        }
    ],
    store: Ext.create('Ext.data.JsonStore', {
        autoLoad: true,
        storeId: 'centerStore',
        fields: ['id', 'userid', 'useridtype', 'name', 'nickname','age','gender','mobile','mail','occupation','address','viplevel','location','headposter','updateby', 'updatetime'],
        proxy: {
            type: 'ajax',
            url: path + '/abc/uc_management/usermanage/list.do',
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
            handler: function () {
                Ext.getCmp('userForm').getForm().reset();
                Ext.getCmp('userForm').getForm().findField('userid').setReadOnly(false);
                Ext.getCmp('userForm').getForm().findField('useridtype').setReadOnly(false);
                userPop.setTitle('增加');
                userPop.show();
            }
        },
        '-',
        {
            xtype: 'button',
            text: '编辑',
            handler: function () {
                var models = centerPanel.getSelectionModel().getSelection();
                if (models.length <= 0) {
                    Ext.Msg.alert('系统提示', '请选择要编辑的数据');
                    return;
                }
                userPop.setTitle('编辑');
                userPop.show();
                Ext.getCmp('userForm').loadRecord(models[0]);
                Ext.getCmp('userForm').getForm().findField('userid').setReadOnly(true);
                Ext.getCmp('userForm').getForm().findField('useridtype').setReadOnly(true);
            }
        },
        '-',
        {
            xtype: 'button',
            text: '删除',
            handler: function () {
                var models = centerPanel.getSelectionModel().getSelection();
                if (models.length <= 0) {
                    Ext.Msg.alert('系统提示', '请选择要删除的数据');
                    return;
                }
                Ext.Msg.confirm('系统提示', '您确认要删除吗?', function (option) {
                    if ('yes' === option) {
                        deleteUser(models);
                    }
                });
            }
        },
        '-',
        {
            xtype: 'textfield',
            fieldLabel: '手机号',
            name: 'usermobile',
            id: 'usermobile'
        },
        {
            xtype: 'textfield',
            fieldLabel: '邮箱',
            name: 'usermail',
            id: 'usermail'
        },
        {
            xtype: 'button',
            text: '查询',
            handler: function () {
                centerPanel.getStore().load({url: path + '/abc/uc_management/usermanage/getuser.do',
                    params: {usermail: Ext.getCmp('usermail').getValue(),usermobile: Ext.getCmp('usermobile').getValue()}});
                centerPanel.clearValue();
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
            fn: function () {
                var models = centerPanel.getSelectionModel().getSelection();
                if (models.length <= 0) {
                    Ext.Msg.alert('系统提示', '请选择要编辑的数据');
                    return;
                }
                userPop.setTitle('编辑');
                userPop.show();
                Ext.getCmp('userForm').loadRecord(models[0]);
                Ext.getCmp('userForm').getForm().findField('userid').setReadOnly(true);
                Ext.getCmp('userForm').getForm().findField('useridtype').setReadOnly(true);
            }
        }
    }
});

function deleteUser(records) {
    Ext.Ajax.request({
        url: path + '/abc/uc_management/usermanage/delUser.do',
        scope: this,
        async: true,
        method: "POST",
        params: { id: records[0].get('id'), adminName: adminName},
        success: function (response, options) {
            Ext.Msg.alert("系统提示", "删除成功");
            centerPanel.getStore().reload();
        },
        failure: function (form, action) {
            Ext.Msg.alert('系统提示', action.result.msg);
        }
    });
}

//domReady
Ext.onReady(function () {
    Ext.create('Ext.container.Viewport', {
        layout: 'border',
        items: [centerPanel]
    });
});