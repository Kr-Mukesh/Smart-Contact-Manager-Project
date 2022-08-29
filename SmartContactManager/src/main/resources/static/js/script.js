console.log("This is Script File")


const toggleSidebar = () => {
	if ($(".sidebar").is(":visible")) {

		$(".sidebar").css("display", "none")
		$(".content").css("margin-left", "0%")
	} else {
		$(".sidebar").css("display", "block")
		$(".content").css("margin-left", "20%")
	}

};

const search = () => {

	//console.log("Searching...");
	let query = $("#search-input").val();
	console.log(query);

	if (query == '') {
		$(".search-result").hide();

	} else {

		console.log(query);

		let url = `http://localhost:9191/search/${query}`;

		fetch(url).then((response) => {

			return response.json();

		}).then(data => {

			//console.log(data);

			let text = `<div class='list-group'>`

			data.forEach((contact) => {
				text += `<a href='/user/${contact.cId}/contact' class='list-group-item list-group-item-action'>${contact.name}</a>`

			});

			text += `</div>`;

			$(".search-result").html(text);

			$(".search-result").show();
		});


	}

};

// first request to server to create order

const paymentStart = () => {
	console.log("payment started..");
	let amount = $("#payment_field").val();
	console.log(amount);

	if (amount == "" || amount == null) {
		//alert("Amount Required !!");
		
		swal("Failed !", "Amount is Required !!", "error")
		return;
	}

	$.ajax({

		url: '/user/create_order',
		data: JSON.stringify({ amount: amount, info: 'order_request' }),
		contentType: 'application/json',
		type: 'POST',
		dataType: 'json',
		success: function(response) {
			console.log(response)

			if (response.status == "created") {

				let options = {
					key: 'rzp_live_EH2VELIZaLWdsh',
					amount: response.amount,
					currency: 'INR',
					name: 'Smart Contact Manager',
					description: 'Donation',
					image: 'https://cdn.searchenginejournal.com/wp-content/uploads/2020/03/the-top-10-most-popular-online-payment-solutions-5e9978d564973-1520x800.png',
					order_id: response.id,

					handler: function(response) {
						console.log(response.razorpay_payment_id);
						console.log(response.razorpay_order_id);
						console.log(response.razorpay_signature);
						console.log('Payment Successfull !!');
						//alert("Congrates !! Payment Successfull !!");
						
						updatePaymentOnserver(response.razorpay_payment_id, response.razorpay_order_id, 'paid')
						
						swal("Congrates !", "Payment Successfull !!", "success")
					},

					"prefill": {
						"name": "",
						"email": "",
						"contact": "",

					},
					
					notes:{
						address:"LearnCodeWithMukesh",
					},
					
					theme:{
						color:"#3399cc",
					},

				};
				
				let rzp = new Razorpay(options);
				
				rzp.on('payment.failed', function(response){
					console.log(response.error.code);
					console.log(response.error.description);
					console.log(response.error.source);
					console.log(response.error.step);
					console.log(response.error.reason);
					console.log(response.error.metadata.order_id);
					console.log(response.error.metadata.payment_id);
					//alert("Oops Payment Failed !!")
					
					swal("Failed !", "Oops Payment Failed !!", "error")
				});
				
				rzp.open();
			}

		},

		error: function(error) {

			console.log(error);
			alert("Something Went Wrong !!");
		},
	});


};

function updatePaymentOnserver(payment_id, order_id, status){
	
	$.ajax({

		url: '/user/update_order',
		data: JSON.stringify({ payment_id: payment_id, order_id: 'order_id', status: 'status' }),
		contentType: 'application/json',
		type: 'POST',
		dataType: 'json',
		
		success:function(response){
			swal("Congrates !", "Payment Successfull !!", "success")
		},
		error:function(error){
			swal("Failed !", "Your Payment is Successfull But We Didn't Capture It..' !!", "error")
		},
		
		});
}





