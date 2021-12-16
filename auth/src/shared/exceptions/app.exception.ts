class AppException {
    public readonly message: string;
    public readonly code: number;
    public readonly payload: any;

    constructor(message?: string, code?: number, payload?: any) {
        this.message = message || 'Error by user app!';

        this.code = code || 400;

        this.payload = payload || {};
    }
}

export { AppException };
