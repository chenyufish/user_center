declare namespace API {
  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    description?: string;
    message?: string;
  };

  type BaseResponseInt_ = {
    code?: number;
    data?: number;
    description?: string;
    message?: string;
  };

  type BaseResponseListUser_ = {
    code?: number;
    data?: User[];
    description?: string;
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    description?: string;
    message?: string;
  };

  type BaseResponseUser_ = {
    code?: number;
    data?: User;
    description?: string;
    message?: string;
  };

  type searchUsersUsingGETParams = {
    /** username */
    username?: string;
  };

  type User = {
    avatarUrl?: string;
    createTime?: string;
    email?: string;
    gender?: number;
    id?: number;
    isDelete?: number;
    phone?: string;
    planetCode?: string;
    updateTime?: string;
    userAccount?: string;
    userPassword?: string;
    userRole?: number;
    userStatus?: number;
    username?: string;
  };

  type UserLoginRequest = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserRegisterRequest = {
    checkPassword?: string;
    userAccount?: string;
    userPassword?: string;
  };
}
