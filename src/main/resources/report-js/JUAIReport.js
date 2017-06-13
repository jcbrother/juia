var nextSnapshootIdx = 1;

//点击用例标题事件
function showCaseDetails(e){
	// $("#" + e.id).css("color","blue");
	// $("#" + e.id).siblings().css("color","black");
	$("#" + e.id).css("background","#DDDDDD");
	$("#" + e.id).siblings().css("background","none");
	var detailsId = "#" + e.id + "-details";
	$(".case-details").css("display","none");
	$(detailsId).animate({opacity:'toggle'},"slow").css("display","inline");
	nextSnapshootIdx = 1;
	$(detailsId).find("#snapshoot-playing").attr("src", var1 = $(detailsId).find("#snapshoot-list").children("li").eq(0).text() === "" ? "../../report-src/default-pic.png": var1);
}

//获取轮播图数据
function getCaseSnapshoots(e){
	var eId = "#" + e.parentNode.parentNode.id;
	var snapshoots = $(eId).find("#snapshoot-list").children("li");
	return snapshoots;
}

//获取图片展示元素
function getSnapshootDisplayElement(e){
	var eId = "#" + e.parentNode.parentNode.id;
	var snapshootDisplayElement = $(eId).find("#snapshoot-playing");
	return snapshootDisplayElement;
}

//显示下一张图片
function showNextSnapshoot(e){
	var snapshoots = getCaseSnapshoots(e);
	if(nextSnapshootIdx<snapshoots.length){
		var picName = snapshoots.eq(nextSnapshootIdx).text();
		nextSnapshootIdx++;
		var snapshootDisplayElement = getSnapshootDisplayElement(e);
		snapshootDisplayElement.animate({opacity:'toggle'},"slow",null,
			function(){
				snapshootDisplayElement.attr("src",picName);
				snapshootDisplayElement.animate({opacity:'toggle'},"slow");
			}
		);
		snapshootDisplayElement.siblings("#pic-name").html(picName);
		snapshootDisplayElement.siblings("#pic-idx").html((nextSnapshootIdx) + "/" + snapshoots.length);
	}
}

//显示前一张图片
function showPreviousSnapshoot(e){
	var snapshoots = getCaseSnapshoots(e);
	if(nextSnapshootIdx>1){
		var picName = snapshoots.eq(nextSnapshootIdx-2).text();
		nextSnapshootIdx--;
		var snapshootDisplayElement = getSnapshootDisplayElement(e);
		snapshootDisplayElement.animate({opacity:'toggle'},"slow",null,
			function(){
				snapshootDisplayElement.attr("src",picName);
				snapshootDisplayElement.animate({opacity:'toggle'},"slow");
			}
		);
		snapshootDisplayElement.siblings("#pic-name").html(picName);
		snapshootDisplayElement.siblings("#pic-idx").html((nextSnapshootIdx) + "/" + snapshoots.length);
	}
}