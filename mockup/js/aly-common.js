function shu(x,m,n){

    if(m == undefined)
        m = "";
    else
        m = m + "_";

    for(i=1;i<=n;i++){
        if(x==i){
            document.getElementById("table_" + m + i).style.display="block";
            document.getElementById("s_" + m + i).className="sx_" + m + i + "_1";
        }else{
            document.getElementById("table_" + m + i).style.display="none";
            document.getElementById("s_" + m + i).className="sx_" + m + i;
        }
    }
}
//设为首页
function SetHome(obj,vrl){
    try{
        obj.style.behavior='url(#default#homepage)';
        obj.setHomePage(vrl);
    }catch(e){
        if(window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            }catch (e){
                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'");
            }
            var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
            prefs.setCharPref('browser.startup.homepage',vrl);
        }
    }
}

//加入收藏JS
function AddFavorite(sURL, sTitle){
    try{
        window.external.addFavorite(sURL, sTitle);
    }catch (e){
        try{
            window.sidebar.addPanel(sTitle, sURL, "");
        }catch (e){
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}