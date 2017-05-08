import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

/**
 * @author littleredhat
 * @description ªÒ»°∞Ê±æ
 */
public class VersionTest {

	public static void main(String[] args) throws Exception {
		// defaults to http://localhost:8545/
		Web3j web3 = Web3j.build(new HttpService());

		// To send asynchronous requests using a Future
		Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();
		System.out.println(clientVersion);
	}
}
