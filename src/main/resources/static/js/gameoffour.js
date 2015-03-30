$(function () {

    var squares = [], 
        SIZE = 6,
        EMPTY = "&nbsp;",
        score,
        moves,
        turn = "R",

    startNewGame = function () {
        turn = "R";
        score = {"R": 0, "G": 0};
        squares.forEach(function (square) {square.html(EMPTY);});
    },

    
    fetchpoints= function(){
    	 $.ajax({url: '/games/initboard', 
    		 async: false, 
    		 dataType: "json", 
    		 success: function(data) { 
    			 $.each(data, function( index, value ) {
    				 console.log(" " + value.posX + " " + value.posY); 
    				 $("#x"+value.posX+"y"+value.posY).html(value.turn);
    			  });
    		 }, 
    		 error: function(http, message, exc) {
    			alert("Failed") 
    		 }
    	 
    		    // time = new Date();
    		 });
    },
    
    putColor= function(column,player){
    		$.ajax({url: '/games/'+player+'/play/'+column, 
    		 async: false, 
    		 dataType: "json", 
    		 success: function(data) {
    			 console.log(data);
    			 if(data.message==='Red')
    				 alert('Red Winns');
    			 else if (data.message==='Green')
    				 alert('Green Winns');
    			 else if (data.message==='Tie')
    				 alert('Tie');
    			// $.each(data, function( index, value ) {
    				// console.log(" " + value.posX + " " + value.posY); 
    				// $("#x"+value.posX+"y"+value.posY).html(value.turn);
    			 // });
    			 console.log("done");
    		 }, 
    		 error: function(http, message, exc) {
    			//alert("Failed") 
    		 }
    	 
    		    // time = new Date();
    		 });
    }
    
    	

    set = function () {
    	var player=$("#currentPlayer").val();
    	var column=$(this)[0].indicator
    	putColor(column,player);
    	fetchpoints();
        
    },
    
    

  
    play = function () {
        var board = $("<table border=1 cellspacing=0>");
        for (var i = 0; i < SIZE+1; i += 1) {
            var row = $("<tr>");
            board.append(row);
            for (var j = 0; j < SIZE; j += 1) {
                var cell = $("<td height=50 width=50 align=center valign=center ><div id='x"+i+"y"+j+"'/></td>");
                cell[0].indicator = j;
                cell.click(set);
                row.append(cell);
                squares.push(cell);
            }
        }

        $(document.getElementById("connectfour") || document.body).append(board);
        
        fetchpoints();
    };

    play();
    
  
});

