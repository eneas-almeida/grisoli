import { HashProviderStrategy } from './hash.provider/hash.provider.strategy';
import { TokenProviderStrategy } from './token.provider/token.provider.strategy';

new HashProviderStrategy().setStrategy('bcrypt');
new TokenProviderStrategy().setStrategy('jwt');
