<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<div id="fb-root">




</div>
<button class="btn btn-success" id="fb-login">Login</button>
<h1>Defaults</h1>
<fb:like></fb:like>

<h1>feed</h1>
<p>
Publishing to the stream is easy, as all the fields are optional. Just specify
what you need, and leave the rest out.
</p>


<script src="http://connect.facebook.net/en_US/all.js" language="JavaScript" type="text/javascript"></script>
<script>

  window.fbAsyncInit = function() {
    // init the FB JS SDK
    FB.init({
      appId      : '1428173107404415',                        // App ID from the app dashboard
      channelUrl : '//WWW.YOUR_DOMAIN.COM/channel.html', // Channel file for x-domain comms
      status     : true,                                 // Check Facebook Login status
      xfbml      : true                                  // Look for social plugins on the page
    });
  };
  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/all.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
  function showUserInfo(id) {
      FB.api({
        method: 'fql.query',
        query: 'SELECT name, pic_square FROM user WHERE uid='+id
      },function(response) {
        document.getElementById('userInfo').innerHTML += (
          '<img src="' + response[0].pic_square + '"> ' + response[0].name
        );
      });
  }
  document.getElementById('fb-login').onclick = function() {
	  FB.login(function(response) {
	    Log.info('FB.login callback', response);
	    if (response.status === 'connected') {
	      Log.info('User is logged in');
	    } else {
	      Log.info('User is logged out');
	    }
	  });
	};
	
	  var publish = {
	    method: 'feed',
	    message: 'getting educated about Facebook Connect',
	    name: 'Connect',
	    caption: 'The Facebook Connect JavaScript SDK',
	    description: (
	        'A small JavaScript library that allows you to harness ' +
	        'the power of Facebook, bringing the user\'s identity, ' +
	        'social graph and distribution power to your site.'
	    ),
	    link: 'http://www.fbrell.com/',
	    picture: 'http://www.fbrell.com/public/f8.jpg',
	    actions: [
	      { name: 'fbrell', link: 'http://www.fbrell.com/' }
	    ],
	    user_message_prompt: 'Share your thoughts about RELL'
	  };

	  FB.ui(publish, Log.info.bind('feed callback'));
</script>
</body>
</html>