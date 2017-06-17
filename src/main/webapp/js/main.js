    $(function() {
        //init variable:
        //userObj
        var user ;
        //userid
        var userId ;
        //used bookset
        var booksetId;
        //mark of voucher
        var markOfMark;
        //the Voucher Table
        var voucherTable;
        var voucherSummaryTable;
        var customSubjectTable;
        
        var vt_select;
        var vst_select; 
        
        var tableInitRepeat = 0;
//-----------------------------------------------------------------------------------------------------
        		window.initMain = function (a,b){
        			user = a;
        			userId = b;
        		};
//-----------------------------------------------------------------------------------------------------Bookset
                //use of bookset
                window.useBookset = function(str,num){
	                if(booksetId != num){
	                	$("#nowbookset").text(str);
	                	booksetId = num;
	                	var initAccountPeriod = $("#accounteidt").val();
	                	queryMarkOfVoucher(num,initAccountPeriod);
	                	var temp = queryAccountPeriod(num);
	                	clearVoucherContent();
	                		if(tableInitRepeat == 0) {
	                			initSummaryVoucherTable();
	                			initVoucherTable();
	                			initTableSelect(temp);
	                			tableInitRepeat = 1;
	                		}else{
	                			initAllTable();
	                		}
		                    $("#myModal").modal("hide");
	                    }else{
	                       $("#myModal").modal("hide");
	                    }
                };
                
                //delete of bookset
                window.delBookset = function(num,str){
                    if(booksetId!=num){
                        if(confirm("你确定要删除账套:"+str+"吗?")){
                            $.ajax({
                                type:"post",
                                url:"deletebookset",
                                data:"booksetid="+num,
                                success:function(data){
                                    if(eval('('+data+')').result){
                                        $("#"+num+"").find("*").remove();
                                        
                                    }else{
                                    	alertError("删除失败,请联系系统管理员");
                                    }
                                },
                                error:function(){
                                	alertError("删除账套服务调用失败,请联系系统管理员");
                                }
                            });
                        };
                    }else{
                        alertMessage("正在使用的账套无法删除！","info");
                    }
                };
                
                //show all bookset
                window.showBooksetList = function (){
                    $.ajax({
                        type:"post",
                        url:"selectbookset",
                        data:"userid="+userId,
                        success:function(data){
                        var obj = eval('(' + data+ ')');
                        var abl = $("#addbooksetlocal");
                        var bhtml ="<thead><th>序号</th><th>公司名称</th><th>使用</th><th>删除</th></tr></thead><tbody>";
                        for(var i = 0 ;i < obj.BooksetList.length;i++){
                            bhtml += "<tr id='"+obj.BooksetList[i].booksetid+"'>"+
                                        "<td>"+(i+1)+"</td>"+
                                        "<td><b>"+obj.BooksetList[i].compname+"</b></td>"+
                                        "<td><button type='button' onclick=useBookset('"+obj.BooksetList[i].compname+"','"+obj.BooksetList[i].booksetid+"'); class='btn btn-success'>使用</button></td>"+
                                        "<td><button type='button' onclick=delBookset('"+obj.BooksetList[i].booksetid+"','"+obj.BooksetList[i].compname+"'); class='btn btn-danger'>删除</button></td>"+
                                        "<td hidden='true'>"+obj.BooksetList[i].booksetid+"</td></tr>";

                        }
                        abl.append(bhtml+"</tbody>");
                        },
                        error:function(data){
                        	alert(data.hang);
                        	alertError("账套列表服务调用失败,请联系系统管理员");
                        }
                    });
                    $("#myModal").modal("show");
                };
                
                //add of bookset
                $("#modalSubmit").click(function(){
                    if($("#compname").val() == ""){
                        alertMessage("请输入公司名称","error");return;                    
                    }
                    $.ajax({
                        type:"post",
                        url:"addBookset",
                        data:"userid="+userId+"&compname="+$("#compname").val(),
                        success:function(data){
                        var obj = eval('(' + data+ ')');
                            if($.isEmptyObject(obj.success)){
                            	alertError("账套创建失败,请联系系统管理员");
                            }else{
                                bookset = obj.success;
                                $("#addbooksetlocal").find("*").remove();
                                showBooksetList();
                                useBookset(bookset.compname,bookset.booksetid);
                                
                            }
                        },
						error:function(){
							alertError("增添账套服务调用失败,请联系系统管理员");
                       }
                    });
                });
//-----------------------------------------------------------------------------------------------------Bookset Over             
                
//-----------------------------------------------------------------------------------------------------Voucher Item
                //add a row OR del a row
                //select2 structure
                var selectClone = $(".exampleRow:last").find(".select2:last").clone();
                window.addARow = function(){
                    var addrow = $(".exampleRow:last").clone();
                        addrow.find("div").eq(1).children().remove();
                        addrow.find("div").eq(1).append(selectClone.clone());
                        addrow.find("input").val("");
                        $(".exampleRow:last").after(addrow);
                        addrow.find(".select2:last").select2();     
                };
                window.delARow = function(){
                        var trLen =  $("#voucherTable").find("tr").length ;
                        if(trLen > 3)
                        $("#voucherTable").find("tr:eq("+(trLen-2)+")").remove() ;
                };
                $("#addARow").click(function(){
                        addARow();
                });
                $("#delARow").click(function(){
                        delARow();
                });
                
                //query mark of voucher
                function queryMarkOfVoucher(num,date){
                    $.ajax({
                        type:"post",
                        url:"queryNumOfVoucher",
                        data:"booksetid="+num+"&accountperiod="+date,
                        success:function(data){
                        var obj = eval('(' + data+ ')');
                        $("#numofvoucher").val(obj.num);
                        markOfVoucher = obj.num;
                        }
                    });
                }
                
                  
                  //condition judgement ==>> submit voucher  (1)
                  window.voucherLogicJudge = function(){
                        //init voucher_it
                        var arr = new Array();
                        var debit = 0; var credit = 0;
                        var input1; var input2; var input3; var select1;
                        var subjectid; var isload; var money; var note;
                        
                        //loop load voucher_it
                        var len = $(".exampleRow").length;
                        for(var i = 0; i < len; i++){
                            var newObj ;var thisrow;
                            thisrow = $(".exampleRow").eq(i);
                            input1 = thisrow.find("input").eq( 0 );
                            select1 = thisrow.find("select").eq( 0 );
                            input2 = thisrow.find("input").eq( 1 );
                            input3 = thisrow.find("input").eq( 2 ); 
                            if(input2.val() == 0)input2.val("");
                            if(input3.val() == 0)input3.val("");
                            
                            if(i == 0){
                                if(input1.val() == "") { alertMessage("第一条分录备注不能为空", "error");exit();}
                                if(select1.val() == null || (input2.val() == "" && input3.val() == "")) { alertMessage("第一条分录不能为空", "error");exit();}
                            }
                            if(input2.val() != "") {
                                isload = false;
                                money = parseFloat(getmoneyformat(input2.val()));
                                subjectid = getsubjectid(select1.val());
                                debit += money;
                            }else if(input3.val() != "") {
                                isload = true;
                                money = parseFloat(getmoneyformat( input3.val() ));
                                subjectid = getsubjectid( select1.val() );
                                credit += money;
                            }else if(select1.val() != null) {
                                 alertMessage("请填写借贷金额", "error"); exit();
                            }else {
                                continue;
                            }
                            
                            if(subjectid == null){
                                alertMessage("请填写会计科目","error");exit();
                            }
                            
                            note = input1.val();
                            newObj = {'note':note,'subjectid':subjectid,'isload':isload,'money':money};
                            arr.push(newObj);
                        }
                        
                        if(debit != credit){ alertMessage("借贷不相等","error");exit(); } 
                        var voucher = {'booksetid':booksetId,'itemid':$("#numofvoucher").val(),'accountperiod':$("#accounteidt").val(),
                                        'docNum':$("#docNum").val(),'date':$("#dateview").val()};
                        var obj ={"voucher":voucher,"voucher_it":arr};
                        return obj;
                  };
                  
                  //format money  ==>> submit voucher  (2)
                  window.getmoneyformat = function(str){
                  if(str == "") {
                       return null;
                  }else{
		                  var arr = str.split(",");
		                  var  a  = "";
		                    for(var i= 0 ; i<arr.length;i++){
		                        a += arr[i];
		                    }
		                  return a;
	                    }
                  };
                
                  //conversion to subjectid ==>> submit voucher  (3)
                  window.getsubjectid = function(str){
                    if(str != null){
                        var arr = str.split(" ");
                        return parseInt(arr[0]);
                    }else{
                        alertMessage("未填写会计科目","error");exit();
                    } 
                  };
                  
                  
                  //clear voucher content ==>> submit voucher  (4)
                  window.clearVoucherContent = function(){
                        $("#voucherTable").find("input").val("");
                        $("#voucherTable").find("select").select2().val(null).trigger("change");
                  };
                  
                  //submit voucher (OK)
                  $("#submitvoucher").click(function(){
                        var obj = voucherLogicJudge();
                        $.ajax({
                            type:"post",
                            url:"submitVoucher",
                            contentType:"application/json",
                            dataType:"json",
                            data:JSON.stringify(obj),
                            success:function(data){
                                if(data.success){
                                    alertMessage("凭证录制成功");
                                    queryMarkOfVoucher(booksetId,$("#accounteidt").val());
                                    $(".exampleRow").find("input").val("");
                                    clearVoucherContent();
                                    initAllTable();
                                }else{
                                	alertError("凭证录制失败,请联系系统管理员","error");
                                }
                            },error:function(){
                            	alertError("录制凭证服务调用失败,请联系系统管理员","error");
                            }
                        });
                  });
                    
                                  
                  //delete voucher (1)
                  window.deleteVoucher = function(str1,str2,str3,str4){
                    var obj = {"accountperiod":str1,"itemid":str2,"booksetid":str3,"voucherid":str4};
                        if(confirm("删除！","删除将无法恢复")){  
                            $.ajax({
                                    type:"post",
                                    url:"deleteVoucher",
                                    contentType:"application/json",
                                    data: JSON.stringify(obj),
                                    dataType:"json",
                                    success:function(data){
                                        if(data.status){
                                            alertMessage("删除成功","success");
                                            initAllTable();
                                            $("#confirmbox").modal("hide");
                                        }else{
                                        	alertError("删除失败,请联系系统管理员","error");
                                            $("#confirmbox").modal("hide");
                                        }
                                        
                                    },error:function(){
                                    	alertError("删除凭证服务调用失败,请联系系统管理员","error");
                                    }
                            });
                                    }
                                
                  };
                  
                  //Goto Voucher Edit Page ==>> Update Voucher ( 1 )
                  window.updateVoucher = function(str1,str2,str3,str4,str5){        
                        $("#updatevoucher").css("display","");$("#getupupdatevoucher").css("display","");$("#submitvoucher").css("display","none");
                        $("#numofvoucher").val(str2);$("#numofvoucher").attr("disabled","disabled");$(".updown").attr("disabled","disabled");
                        $("#dateview").val(str5);$("#dateview").attr("disabled","disabled");
                        $("#accounteidt").val(str1);
                        $("#updateVoucherId").val(str4);
                        queryVoucher_it(str4);
                        $("#table-menu a[href='#one']").tab("show");
                        $("#voucherEdit").text("凭证修改");
                  };
                  
                  //List Specify Voucher Details ==>> Update Voucher ( 2 )
                  window.queryVoucher_it = function(num){
                    $.ajax({
                        type:"post",
                        url:"queryVoucher_It",
                        data:"voucherid="+num,
                        dataType:"json",
                        success:function(data){
                             clearVoucherContent();
                            var glen = $(".exampleRow").length;
                            var arr = data.Voucher_ItList;
                            if(arr.length>glen){
                                for(var i = arr.length; i > glen; i--){addARow();};
                            }else{ 
                                for(var i = arr.length; i < glen; i++){delARow();};
                            }
                            var input1; var input2; var input3; var select1;
                            for(var i = 0; i <arr.length ; i++){
                                input1 = $(".exampleRow").eq(i).find("input").eq(0);select1 = $(".exampleRow").eq(i).find("select").eq(0);
                                input2 = $(".exampleRow").eq(i).find("input").eq(1);input3 = $(".exampleRow").eq(i).find("input").eq(2);
                                input1.val(arr[i].note);
                                if(arr[i].isload){
                                    input3.val(arr[i].money);
                                }else{
                                    input2.val(arr[i].money);
                                }
                                select1.select2().val(arr[i].subjectid).trigger("change");
                            }
                        }
                    });
                  };
                
                  //Update Voucher ( OK )
                  $("#updatevoucher").click(function(){
                        var obj = voucherLogicJudge();
                        obj.voucher.voucherid = $("#updateVoucherId").val();
                        $.ajax({
                            type:"post",
                            url:"updateVoucher",
                            contentType:"application/json",
                            dataType:"json",
                            data:JSON.stringify(obj),
                            success:function(data){
                                if(data.status){
                                    alertMessage("修改成功","success");
                                    $("#updateVoucherId").val("");
                                    initAllTable();
                                    getupupdatevoucher1();
                                }else{
                                	alertError("修改失败,请联系系统管理员");
                                    $("#updateVoucherId").val("");
                                }
                            },error:function(){
                            		alertError("更新凭证服务调用失败,请联系系统管理员");
                            }
                        });
                  });
//-----------------------------------------------------------------------------------------------------Subject 
                  window.deleteCustomSubject = function(num,id){
                	  $.ajax({
                		 type:"post",
                		 url:"deleteCustomSubject",
                		 data:"subjectid="+num+"&userid="+id,
                		 success:function(data){
                			 if(eval('('+data+')').status){
                				 alertMessage("删除科目成功","success");
                				 loadCustomSubject();
                			 }else{
                				 alertMessage("删除科目失败","error");
                			 }
                		 },error:function(){
                			 alertError("删除科目服务调用失败,请联系系统管理员");
                		 }
                	  });
                  };
                  
                  $("#subjectNumber").blur(function(){
                      if($("#subjectNumber").val() != "" && $("#subjectNumber").val() != null) {
                          $.ajax({
                              type:"post",
                              url:"getCountOfSubjectAdd",
                              data:"subjectid="+a+"&userid="+userId,
                              success:function(data){
                                  if(eval("("+data+")").status){
                                      $("#subjectNumberTool").text("科目编码已存在,请更换");
                                      $("#addSubjectAdd").attr("disabled", "disabled");
                                  }else{
                                	  $("#subjectTool").text("");
                                  }
                              }
                          });
                      }
                  });
                  
                  $("#addSubjectAdd").click(function(){
                	  var num = $("#subjectNumber").val();
                	  var name = $("#subjectName").val();
                	  var cate = $("#subjectSelect").val();
                	  var isload = $("input[name='isload']:checked").val();
                	  $.ajax({
                		 type:"post",
                		 url:"saveCustomSubject",
                		 data: "subjectid=" + $("#subjectNumber").val() + "&subjectname=" + $("#subjectName").val() +
                			 "&isload=" + $("input[name='isload']:checked").val() + "&subcatid=" + $("#subjectSelect").val() +
                			 "&userid=" + userId,
                		 success:function(data){
                			 alertMessage("添加科目成功","success");
                			 $(".addSubject").val("");
                			 $("#subjectSelect").val("0");
                			 loadCustomSubject();
                		 },error:function(){
                			 alertError("添加科目服务调用失败,请联系系统管理员");
                		 }
                	  });
                  });
                  window.verifyForm = function(){
                	  if($("#subjectNumber").val().length != 0 ){
                		  $("#subjectNumberTool").text("");
                  		 if($("#subjectName").val().length != 0){
                  			$("#subjectNameTool").text("");
                  			 if($("#subjectSelect").val() != null){
                  				$("#subjectCateTool").text("");
                  				$("#addSubjectAdd").attr("disabled", null);
                  			 }else{
                  				 $("#subjectCateTool").text("请选择科目类别");
                  				$("#addSubjectAdd").attr("disabled", "disabled");
                  			 }
                  		 }else{
                  			 $("#subjectNameTool").text("请填写科目名称");
                  			$("#addSubjectAdd").attr("disabled", "disabled");
                  		 }
                 	  }else{
                 		 $("#subjectNumberTool").text("请填写科目编码");
                 		$("#addSubjectAdd").attr("disabled", "disabled");
                 	  }
                  };
                  $(".addSubject").blur(function(){
                	  verifyForm();
                  });
                  $("#subjectSelect").change(function(){
                	  verifyForm();
                  });
 //-----------------------------------------------------------------------------------------------------Subject Over
//-----------------------------------------------------------------------------------------------------Voucher Table
                  //Init DataTable Details  ==>>  VoucherTable And VoucherSummaryTable
                  var lang = {
                            "sProcessing": "处理中...",
                            "sLengthMenu": "每页 _MENU_ 项",
                            "sZeroRecords": "没有匹配结果",
                            "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
                            "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
                            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                            "sInfoPostFix": "",
                            "sSearch": "搜索:",
                            "sUrl": "",
                            "sEmptyTable": "表中数据为空",
                            "sLoadingRecords": "载入中...",
                            "sInfoThousands": ",",
                            "oPaginate": {
                                "sFirst": "首页",
                                "sPrevious": "上页",
                                "sNext": "下页",
                                "sLast": "末页",
                                "sJump": "跳转"
                            },
                            "oAria": {
                                "sSortAscending": ": 以升序排列此列",
                                "sSortDescending": ": 以降序排列此列"
                                }   
                            };
                  //Init DateTime Format  ==>>  VoucherTable And VoucherSummaryTable
                  Date.prototype.Format = function(fmt) {
                        var o = {
                            "M+": this.getMonth() + 1,
                            //月份
                            "d+": this.getDate(),
                            //日
                            "h+": this.getHours(),
                            //小时
                            "m+": this.getMinutes(),
                            //分
                            "s+": this.getSeconds(),
                            //秒
                            "q+": Math.floor((this.getMonth() + 3) / 3),
                            //季度
                            "S": this.getMilliseconds() //毫秒
                        };
                        if (/(y+)/.test(fmt)) {
                            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                        }
                        for (var k in o) {
                            if (new RegExp("(" + k + ")").test(fmt)) {
                                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                            }
                        }
                        return fmt;
                    };
                    
                    //query voucher of account period  ==>>  VoucherTable And VoucherSummaryTable
                  window.queryAccountPeriod = function(num){
                	  var result ;
                    $.ajax({
                        type:"post",
                        url:"queryAccountPeriod",
                        data:"booksetid="+num,
                        async:false,
                        success:function(data){
                        	result = eval('('+data+')');
                            vt_select = result.accPerList[0].accountperiod;
                            vst_select = vt_select;
                            
                        },error:function(){
                        	alertError("获取账期服务调用失败,请联系系统管理员","error");
                        }
                    });
                    return result;
                  };
                  window.initTableSelect = function(obj){
                	  $("#selectAccountPeriod").children().remove();
                      $("#selectAccountPeriod1").children().remove();
                      var a ; var b ; 
                      for(var i = 0;i<obj.accPerList.length;i++){
                          a +="<option>"+obj.accPerList[i].accountperiod+"</option>";
                          b +="<option>"+obj.accPerList[i].accountperiod+"</option>";
                      }
                      $("#selectAccountPeriod").append(a);
                      $("#selectAccountPeriod1").append(b);
                  };
                  
                  window.loadVoucher = function(){
                        vt_select = $("#selectAccountPeriod").val();
                        repeat = -1;
                        voucherTable.clear();
                        voucherTable.ajax.reload();
                        voucherTable.draw();
                  };
                  window.loadVoucherSummary = function(){
                        vst_select = $("#selectAccountPeriod1").val();
                        voucherSummaryTable.clear();
                        voucherSummaryTable.ajax.reload();
                        voucherSummaryTable.draw();
                        
                  };
                  window.initAllTable = function(){
                        loadVoucher();
                        loadVoucherSummary();
                        queryAccountPeriod(booksetId);
                  };
                  window.loadCustomSubject = function(){
                	  customSubjectTable.ajax.reload().draw();
                  };
                //Voucher Table Select  ==>>  Vocher Table
                 
                 //Summary Voucher Table Select  ==>>  Voucher Summary Table
                 $("#selectAccountPeriod1").change(function(){
                        loadVoucherSummary();
                 });
                 //Open Table Detail Info  ==>>  Voucher Summary Table  
                 $('#voucherSummaryTable tbody').on('click', 'td.details-control', function () {
                    var tr = $(this).closest('tr');
                    var row = voucherSummaryTable.row( tr );
                    if ( row.child.isShown() ) {
                        row.child.hide();
                        tr.removeClass('shown');
                    }else {
                        row.child( format1(row.data()) ).show();
                        tr.addClass('shown');
                    };
                  });
                 
                 //init function goto table filter in queryvoucherpage  ==>>  Voucher Summary Table 
                window.goVoucherTable = function(num){
                    $("#table-menu a[href='#two']").tab("show");
                    voucherTable.search( num ).draw();
                };
                
                //init queryVoucherSummaryTable before  ==>>  Voucher Summary Table
                 window.format1 = function ( b ) {
                   var d = b.list;
                   var str = '<table cellpadding="5" class="table table-hover" cellspacing="0" border="0" style="padding-left:50px;">'+
                                '<tr><th style="width:10px"></th><th >日期</th><th>凭证字号</th><th>备注</th><th>借方金额</th><th>贷方金额</th></tr>';
                   for(var i = 0; i < d.length; i++){
                        str += '<tr>'+'<td><button class="btn btn-info" type="button" onclick = \"goVoucherTable('+d[i].itemid+')\">详情</button></td>'+
                                '<td>'+(new Date(d[i].date.time)).Format("yyyy-MM-dd")+'</td>'+
                                '<td>'+d[i].itemid+'</td><td>'+d[i].note+'</td>';
                        if(d[i].isload){
                            str += '<td>0</td><td>'+d[i].money+'</td></tr>';
                        }else{
                            str += '<td>'+d[i].money+'</td><td>0</td></tr>';
                        }
                    }
                    str += '</table>';
                    return str;
                };
                 
                 window.initSummaryVoucherTable = function(){
                 voucherSummaryTable = $("#voucherSummaryTable").DataTable({
                                language:lang,
                                lengthChange: false,
                                searching: false,
                                filter:false,
                                destroy: true,
                                autoWidth:false,
                                sort:false,
                                ajax:{
                                    type:"post",
                                    url:"listVoucherItemForSummary",
                                    data:function(){
                                    return {"booksetid":booksetId,"accountperiod":vst_select};
                                    },
                                    dataSrc:function(result){
                                        return result.listVoucherItem;
                                    }
                                },
                                columns:[{
                                            class:'details-control',
                                            orderable:false,
                                            data:'null',
                                            defaultContent: '',
                                            width:"15px"
                                        },{
                                            data:"listVoucherItem",width:"250px"
                                            
                                        },{
                                            data:"listVoucherItem",width:"250px"
                                            
                                        },{
                                            data:"listVoucherItem",width:"250px"
                                            
                                        },{
                                            data:"listVoucherItem",width:"250px"
                                            
                                        }],
                                columnDefs:[{
                                                targets:[1],
                                                render:function(data,type,row,meta){
                                                    return row.subjectid;
                                                }
                                            },{
                                                
                                                targets:[2],
                                                render:function(data,type,row,meta){
                                                    return row.subjectname;
                                                }
                                            },{
                                                
                                                targets:[3],
                                                render:function(data,type,row,meta){
                                                    return row.creditmoney;
                                                }
                                            },{
                                                
                                                targets:[4],
                                                render:function(data,type,row,meta){
                                                    return row.debitmoney;
                                                }
                                            }], 
                                "order": [[1, 'asc']]
                         });
                         
                          $('#voucherSummaryTable tbody').on('click', 'td.details-control', function () {
                            var tr = $(this).closest('tr');
                            var row = voucherSummaryTable.row( tr );
                            if ( row.child.isShown() ) {
                                // This row is already open - close it
                                row.child.hide();
                                tr.removeClass('shown');
                            }else {
                                // Open this row
                                row.child( format1(row.data()) ).show();
                                tr.addClass('shown');
                            };
                        } );
                 };
                 
                  //about query voucher table ==>> VoucherTable
                  var repeat = -1;
                  
                  //init VoucherTable
                   window.initVoucherTable = function(){
                      voucherTable = $("#queryVoucherTable").DataTable({
                            dom:'<"#rowone" and fl>rt<"bottom" ip>',
                            language:lang,
                            autoWidth:false,
                            destroy: true,
                            lengthMenu: [[10, 15, -1], [10, 15, "All"]],
                            ajax:{
                                url:"queryVoucherTable",
                                type:"post",
                                data:function(){
                                return {"booksetid":booksetId,"accountperiod":vt_select};
                                },
                                dataSrc:function(result){
                                    return result.AllVoucher;
                                }
                            },
                            columns:[
                                {data:"voucher.date.time",width:"25px"},
                                {data:"voucher.itemid",width:"35px"},
                                {data:"voucher_it.note",width:"200px"},
                                {data:"voucher_it",width:"250px"},
                                {data:"voucher_it",width:"45px"},
                                {data:"voucher_it",width:"45px"},
                                {data:"voucher",width:"100px"}
                            ],
                            columnDefs:[{
                                    targets:0,
                                    render:function(data,type,row,meta){
                                        return (new Date(data)).Format("yyyy-MM-dd");
                                    }
                                },{
                                    searchable: false,
                                    orderable: false,
                                    targets:[2],
                                },{
                                    searchable: false,
                                    orderable: false,
                                    targets:[3],
                                    render:function(data,type,row,meta){
                                        return data.subjectid+"  "+data.subjectname;
                                    }
                                },{
                                    searchable: false,
                                    orderable: false,
                                    targets:[4],
                                    render:function(data,type,row,meta){
                                        if(data.isload){
                                            return null;
                                        }else{
                                            return data.money;
                                        }
                                    }
                                },{
                                    searchable: false,
                                    orderable: false,
                                    targets:[5],
                                    render:function(data,type,row,meta){
                                        if(data.isload){
                                            return data.money;
                                        }else{
                                            return null;
                                        }
                                    }
                                },{
                                    searchable: false,
                                    targets:[6],
                                    orderable: false,
                                    render:function(data,type,row,meta){
                                            if(repeat != data.itemid){
                                                repeat = data.itemid;
                                                var date = (new Date(data.date.time)).Format("yyyy-MM-dd");
                                                return "<button type='button' class='btn btn-sm btn-info' onclick='updateVoucher(\""+data.accountperiod+"\",\""+data.itemid+"\",\""+data.booksetid+"\",\""+data.voucherid+"\",\""+date+"\");'>修改</button>   "+
                                                "<button type='button' class='btn btn-sm btn-danger' onclick='deleteVoucher(\""+data.accountperiod+"\",\""+data.itemid+"\",\""+data.booksetid+"\",\""+data.voucherid+"\");'>删除</button>";
                                            }else{
                                                return null;
                                            }
                                        }
                                }
                            ],
                            rowsGroup:[
                                        0,1
                            ],
                      });
                      $("#rowone").append("<div class='input-group' style='margin-left:350px;width:100px'><span class='input-group-addon' style='width:30px;'>账期</span><select class='form-control t_select' id='selectAccountPeriod' onChange = 'loadVoucher();' style='margin-left:-100px;cellspacing:0;width: 100px'></select></div>");
                 };

                 
                 //init CustomSubjectTable
                 window.initCustomSubjectTable = function(){
                	 customSubjectTable = $("#CustomSubjectTable").DataTable({
                		 language:lang,
                         autoWidth:false,
                         destroy: true,
                         lengthChange:false,
                         searching:false,
                         sort:false,
                         ajax:{
                        	 type:"post",
                        	 url:"listAllCustomSubject",
                        	 data:function(){
                        		 return "userid="+userId;
                        	 },
                        	 dataSrc:function(result){
                        		return result.subject;
                        	 }
                         },
                         columns:[
                                  {data:"subject",width:"200px"},
                                  {data:"subject",width:"200px"},
                                  {data:"subject",width:"200px"},
                                  {data:"subject",width:"200px"},
                                  {data:"subject",width:"200px"}
                                  ],
                         columnDefs:[
                                     {
                                    	 targets:[0],
                                    	 searchable: false,
                                         orderable: false,
                                         render:function(data,type,row,meta){
                                        	 return row.subcatname;
                                         }
                                     },{
                                    	 targets:[1],
                                    	 searchable: false,
                                         orderable: false,
                                         render:function(data,type,row,meta){
                                        	 return row.subjectid;
                                         }
                                     },{
                                    	 targets:[2],
                                    	 searchable: false,
                                         orderable: false,
                                         render:function(data,type,row,meta){
                                        	 return row.subjectname;
                                         }
                                     },{
                                    	 targets:[3],
                                    	 searchable: false,
                                         orderable: false,
                                         render:function(data,type,row,meta){
                                        	 if(row.isload)
                                        		 return "贷";
                                        	 else
                                        		 return "借";
                                        	 
                                         }
                                     },{
                                    	 targets:[4],
                                    	 searchable: false,
                                         orderable: false,
                                         render:function(data,type,row,meta){
                                        	 return "<button type='button' class='btn btn-sm btn-danger' onclick='deleteCustomSubject(\""+row.subjectid+"\",\""+row.userid+"\");'>删除</button>";
                                         }
                                     },
                                     
                                     ],
                	 });
                	 
                 };
                 
                 
                 
                 
//--------------------------------------------------------------------------------------------------Voucher Table Over
//--------------------------------------------------------------------------------------------------Other Plus

                  //init function//alert 
                  //output the messages
                  
				$._messengerDefaults = {
				    extraClasses: 'messenger-fixed messenger-theme-block messenger-on-bottom'
				};
                window.alertMessage = function(str,type){
                    Messenger().post({
                        message: str,
                        type: type,    
                        showCloseButton: true,
                        hideAfter: 3,
                    });
                };
                window.alertError = function(str){
                	Messenger().post({
                        message: str,
                        type: "error",    
                        showCloseButton: true,
                    });
                };

                //init plugin: number spinner
                  $('.spinner .btn:first-of-type').on('click', function() {  
                    $('.spinner input').val( parseInt($('.spinner input').val(), 10) + 1);  
                  });  
                  $('.spinner .btn:last-of-type').on('click', function() {  
                  if($('.spinner input').val() > markOfVoucher)
                        $('.spinner input').val( parseInt($('.spinner input').val(), 10) - 1);  
                  });
                  
                //init plugin: datetime
                window.initDatetime = function(){
                $(".meun-item").click(function() {
                    $(".meun-item").removeClass("meun-item-active");
                    $(this).addClass("meun-item-active");
                    var itmeObj = $(".meun-item").find("img");
                    itmeObj.each(function() {
                        var items = $(this).attr("src");
                        items = items.replace("_grey.png", ".png");
                        items = items.replace(".png", "_grey.png");
                        $(this).attr("src", items);
                    });
                    var attrObj = $(this).find("img").attr("src");
                    attrObj = attrObj.replace("_grey.png", ".png");
                    $(this).find("img").attr("src", attrObj);
                });
                var t = new Date();
                $(".form_datetime").datetimepicker({
                 defaultDate:new Date(t),
                 format: "yyyy-mm-dd",
                 autoclose: true,
                 todayBtn: true,
                 todayHighlight: true,
                 showMeridian: true,
                 pickerPosition: "bottom-left",
                 language: 'zh-CN',//中文，需要引用zh-CN.js包
                 startView: 2,//月视图
                 minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
                 }).datetimepicker('update', new Date()).on("changeDate", function(){
                 var a = $(".form_datetime").val().toString().slice(0,7);
                 $("#accounteidt").val(a);
                    queryMarkOfVoucher(booksetId,a);
                  });
                  //init :accountedit
                 $("#accounteidt").val($(".form_datetime").val().toString().slice(0,7));
                };
                
                  window.getupupdatevoucher1 = function(){
                    $("#updatevoucher").css("display","none");$("#getupupdatevoucher").css("display","none");$("#submitvoucher").css("display","");
                    $("#numofvoucher").attr("disabled",null);$(".updown").attr("disabled",null);
                    $("#dateview").attr("disabled",null);
                    $("#table-menu a[href='#two']").tab("show");
                    $("#voucherEdit").text("凭证编写");
                    $(".form_datetime").datetimepicker('update', new Date());
                    queryMarkOfVoucher(booksetId,$("#accounteidt").val());
                    clearVoucherContent();
                };
                initDatetime();
                //init:select2
                 $("select2").select2();
                 
//--------------------------------------------------------------------------------------------------Other Plus Over

            });
    
//------------------------------------------------------------------------------------------------------------控件事件
     
            //金额栏输入逻辑
            function formatmoney (num){
            return num.replace(/(?!-)(?!\.)[^\d]/g,"");
            }
            function formatmoney2 (num){
            	var debitSum = 0; var creditSum = 0;
            	var len = $(".exampleRow").length;
            	for(var i = 0; i < len; i++){
            		var a = $(".exampleRow").eq(i).find("input").eq(1).val();
            		var b = $(".exampleRow").eq(i).find("input").eq(2).val();
            		if(a != "")
            			debitSum += parseInt(getmoneyformat(a));
            		if(b != "")
            			creditSum += parseInt(getmoneyformat(b));
            	};
            	$("#debitSum").val(debitSum);
            	$("#creditSum").val(creditSum);
            	
                if(isNaN(num)){
                return "";
                }else{
                return Number(num).toFixed(2).replace (/(\d{1,3})(?=(\d{3})+(\.\d*)?$)/g, '$1,');
                }
            }
            function clearenemy(obj,str){
                
                if(str == "u"){
                $(obj).parent().parent().next().find("input").val("");
                }else{
                $(obj).parent().parent().prev().find("input").val("");
                }
            }