import { app } from './app';
import { port } from '@configs/geral.config';

app.listen(port, () => console.log(`Listen in port ${port}!`));
