Ext.onReady(function() {
	
	//Ext.QuickTips.init();
	
	var userName = new Ext.form.TextField({
		name : "name",
		id : "name",
		fieldLabel : "用户名",
		blankText : "请输入用户名",
		allowBlank: false,
		selectOnFocus : true,
		cls: 'login_user',
		anchor: '95%',
		value:''
	});
	
	var password = new Ext.form.TextField({
		cls : "key",
		name : "password",
		id : "password",
		fieldLabel : "密&nbsp;&nbsp;&nbsp;码",
		blankText : "请输入密码",
		allowBlank: false,
		selectOnFocus : true,
		inputType : "password",
		cls: 'login_password',
		anchor: '95%',
		value:""
	});
	
	/*var randCode = new Ext.form.TextField({
		cls : "key",
		name : "randCode",
		id:'randCode', 
		fieldLabel : "验证码",
		blankText : "验证码不能为空",
		width:80, 
		allowBlank: false,
		selectOnFocus : true
	});*/
	
	var form = new Ext.FormPanel({
		region: 'center',
		labelAlign : "right",
		labelWidth : 55,
		labelPad : 0,
		frame : true,
		//items : [{html:'&nbsp'},userName, password,randCode,{html:'&nbsp'}],
		items : [{html:'&nbsp'},userName, password,{html:'&nbsp'}],
		listeners: {
			afterlayout : function(){
				password.focus();
			}
		
		}
	});
	
	userName.on("specialkey", function(f, e) {
		if (e.getKey() == e.ENTER) {
			login();
		}
	}, this);
	
	password.on("specialkey", function(f, e) {
		if (e.getKey() == e.ENTER) {
			login();
		}
	}, this);
	
	var win = new Ext.Window({
		el : "win",
		
		title : "润华运营系统",
		width : 300,
		height : 180,
		layout: 'border',
		resizable : false,
		plain : true,
//		autoScroll : true,
		closable : false,
		border: false,
		buttons : [{
			text : "登录",
			id : "login",
			minWidth: 50,
			handler : login
		}, {
			text: "重置",
			minWidth: 50,
			handler : function() {
				form.getForm().reset();
			}
		}],
		items : [form]
	});

	win.show();
	/*var bd = Ext.getDom('randCode'); 
	var bd2 = Ext.get(bd.parentNode); 
	 bd2.createChild([{
		tag: 'span',
		html: ' <a href="javascript:reloadcode();">'
		}, {
		tag: 'img',
		id: 'safecode',
		src: 'image.jsp',
		align: 'absbottom'
		}, {
		tag: 'span',
		html: '</a>'
		}]);*/
	function login(){

		if (!form.getForm().isValid()) {
			Ext.Msg.alert("提示","请输入用户名和密码",function(){
				userName.focus();
			});
			return;
		}
		
        form.getForm().submit({
			waitTitle : '系统信息',
			url: path + '/login/loginValid',   
 			method : 'post',   //指定发送方式
 			waitMsg:"正在验证登录用户,请稍候..."  ,
			clientValidation: true,
            success:function(form, action){ 
        		if(window.parent){
        			window.parent.location.href = path + '/index.do';
        		}else{
        			window.location.href = path + '/index.do';
        		}
				
            },
            failure: function(form, action) {
            	if(!action.result){
            		Ext.Msg.alert("提示","网络错误,请重新登录");  
            	}else {
            		Ext.Msg.alert("提示",action.result.msg);  
            	}
            	reloadcode();
		    }
    	});  
	}
});
function reloadcode(){//刷新验证码函数
	 var verify = document.getElementById('safecode');
	 verify.setAttribute('src', 'image.jsp?' + Math.random());
	}
