import 'dotenv/config';
import 'reflect-metadata';
import express from 'express';
import cors from 'cors';
import morgan from 'morgan';
import 'express-async-errors';
import '@shared/containers';
import { routes } from '@shared/infra/http/routes';
import { exception } from '@shared/exceptions/exception';

const app = express();

app.use(cors());
app.use(express.json());
app.use(morgan('dev'));
app.use(routes());
app.use(exception);

export { app };
