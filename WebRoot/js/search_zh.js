	            var Search = function(options) {
	                	_self=this;
	                	this.s_sl=options.ob;			  	
	                	this.s_width=(!!options.width)?options.width:$(this.s_sl).width()+2; 
	                	this.s_keyname=options.keyname;
	                	this.s_keyid=options.keyid;
	                	this.s_lilist=options.list;
	                	 
	            }
                Search.prototype.init=function(){
                	var s_keyname=this.s_keyname;
                	var s_keyid=this.s_keyid;
                	var s_list=this.s_lilist;
    				this.draw(this.s_sl,s_list,s_keyname,s_keyid);
    			    this.bindEnvent(s_keyname,s_keyid,s_list);
    			},
	            Search.prototype.draw=function(z_obj,z_list,s_keyname,s_keyid){
  				    var str = this.drawCal(z_list,s_keyname,s_keyid);
  				    $(z_obj).parents(".select2").find(".dropdown-wrapper").html(str);
  				  },
  				Search.prototype.drawCal=function(z_list,s_keyname,s_keyid) {
	  				   var htmls = new Array();
	  				   htmls.push("<div class='searchModel' style='display:none'>");
	  				   htmls.push("<span class='' style='width:"+this.s_width+"px;box-sizing: border-box;position: absolute;z-index: 1051;border: 1px solid #ccd0d6;border-top: 0;display: block;'>");
	  				   htmls.push("<span class='' style='background-color: #f7f7f7;display: block;padding: 4px 8px;'>");
	  				   htmls.push("<input class='searchInput' type='text' placeholder ='请输入关键字搜索' style='border: 1px solid #ccd0d6;height: 24px;outline: none;width: 100%;'>");
	  				   htmls.push("</span>");
	  				   htmls.push("<span class='searchContain' style='display: block;'>");
	  				   htmls.push("<ul class='scrollbar-dynamic scroll-content scroll-content2' role='tree' id='select2-js-item-brand-results' style='list-style: none;margin: 0;padding: 0;max-height: 240px;overflow-y: auto;overflow-x: hidden;background: #fff;'>"); 
	  				   for (i = 0; i < z_list.length; i++) {
	  				    htmls.push("<li class='li_"+i+"' style='cursor: pointer;padding: 6px;line-height: 25px;margin:0' onmouseover='$(this).addClass(\"hover\");' onmouseout='$(this).removeClass(\"hover\")' zid='"+z_list[i][s_keyid]+"'>"+z_list[i][s_keyname]+"</li>");			    
	  				   }
	  				   htmls.push("</ul>"); 
	  				   htmls.push("</span>");
	  				   htmls.push("</span>");
	  				   htmls.push("</div>");			  
	  				   return htmls.join('');
	  				  },
	  				Search.prototype.bindEnvent=function(s_keyname,s_keyid,s_list){
	  					
	  					var searchModel=$(this.s_sl).parents(".select2").find(".dropdown-wrapper").find(".searchModel"); 	
	  					var input = $(this.s_sl).parents(".select2").find(".dropdown-wrapper").find('.searchInput'); 
	  					var suggestWrap = $(this.s_sl).parents(".select2").find(".dropdown-wrapper").find('.searchContain');   
	  					var key="";
	  					var backfun=this.s_backFun;
						var _lidata=s_list;
	  					//下拉框绑定显隐事件
	  				     $(this.s_sl).on("click",function(){ 
	  				     	if($(searchModel).css('display')=='block')
	  				     	{
	  				     		searchModel.hide();
	  				     	}
	  				     	else
	  				     	{
	  				     		$(input).val('');	
	  				     		_self.putindata(_lidata,suggestWrap,backfun,s_keyname,s_keyid);  				     		
	  				     		searchModel.show(); 	  				     		
	  				     		$(input)[0].focus();
	  				     	}
	  				     	
	  				     });
	  				     //搜索框绑定keyup事件

	  				     input.bind('keyup',function(){
	  						   var valText = $.trim(input.val()); 	  						
	  						   if(valText =='')
	  						   {
	  							 _self.putindata(_lidata,suggestWrap,backfun,s_keyname,s_keyid);
	  						   	 return;
	  						   }
	  						 _self.searchFuc(valText,_lidata,suggestWrap,backfun,s_keyname,s_keyid); 
	  						 key = valText; 
	  				     }).on('blur',function(){
	  				     	setTimeout(function(){searchModel.hide();},200);			     	
	  				     });
	  			     
	  				  },
	  				  
	  				Search.prototype.searchFuc=function(keyword,data,suggestWrap,backfun,s_keyname,s_keyid){ 
	  				  	//后台ajax获取搜素keyword有关数据
		  				  var aData = [];        
		  				  
		  	              $.each(data,function(i,value){
		  	              	var str=value[s_keyname];
		  	              	var tag=keyword;
		  	              	if(str.indexOf(tag)!=-1)
		  	              	{
		  	              		aData.push(value);
		  	              	}
		  	              })
		  				  if(aData.length<=0)
		  				  { 
		  				   aData=[{"nodata":"no found!"}];
		  				  } 
		  	            _self.putindata(aData,suggestWrap,backfun,s_keyname,s_keyid);
	  				  },	
	  				  
	  				Search.prototype.putindata=function(data,suggestWrap,backfun,s_keyname,s_keyid){
		  				  //往搜索框下拉建议显示栏中添加条目并显示  
		  				  var li;  
		  				  var tmpFrag = document.createDocumentFragment(); 
		  				  $(suggestWrap).find('ul').html('');  
		  				  for(var i=0; i<data.length; i++)
		  				  { 
		  				   li = document.createElement('LI'); 			   			   
		  				   li.onmouseover = function(){
		  					 $(this).addClass('hover');
		  				   };
		  				   li.onmouseout = function(){
		  					 $(this).removeClass('hover');
		  				   };
		  				   if(data[i].hasOwnProperty(s_keyname)){  
		  				   	li.innerHTML = data[i][s_keyname];
		  				   	li.setAttribute("zid",data[i][s_keyid]);
		  				   	li.onclick = function(){
		  				   	 $(this).parents(".select2").find(".selection").find(".select2-js-item-brand-container").html($(this).text());  
		  				     $(this).parents(".select2").find(".selection").find("input[class='select2-input']").val($(this).attr("zid"));  
		  				     eval(backfun);
		  				     $(this).parents(".searchModel").hide();
		  				   	};
		  				   }	
		  				   else
		  				   {
		  				   	li.innerHTML = data[i]["nodata"];
		  				   	li.setAttribute("zid","");
		  				   }
		  				   li.style.cssText ="cursor: pointer;padding: 6px;line-height: 25px;margin:0"; 
		  				   tmpFrag.appendChild(li); 
		  				  }
			  				$(suggestWrap).find('ul').append(tmpFrag); 
			  				suggestWrap.show(); 
			  				
		  				  }
        
	        