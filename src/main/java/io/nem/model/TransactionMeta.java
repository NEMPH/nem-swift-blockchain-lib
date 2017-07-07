package io.nem.model;

/**
 * {  
   "meta":{  
      "innerHash":{  

      },
      "id":877011,
      "hash":{  
         "data":"6e619b07ef5e288224724b24b7368a5cfa9e25e7d911937b442132889877601e"
      },
      "height":1180351
   },
   "transaction":{  
      "timeStamp":71480111,
      "amount":24985000000,
      "signature":"bbca3d7cb82c21a6ede0c1303f94ad503dfd3d059f0073d906fa3cd6b25705bb1ab54e89bd7c133fd93f60a8c25db1b770837bce0d62d4c133e4553e2598480a",
      "fee":21090086,
      "recipient":"ND2JRPQIWXHKAA26INVGA7SREEUMX5QAI6VU7HNR",
      "type":257,
      "deadline":71487311,
      "message":{  
         "payload":"35646366393737626335663134666230393266",
         "type":1
      },
      "version":1744830465,
      "signer":"2f69c71a7cd584e5f92ff787fb1d68aab53985c577eff6e9061c15768899433c"
   }
}
 * @author Alvin
 *
 */
public class TransactionMeta {
	
	private Meta meta;
	private Transaction transaction;
	

}
