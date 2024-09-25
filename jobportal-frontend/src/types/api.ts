/* eslint-disable */
/* tslint:disable */
/*
 * ---------------------------------------------------------------
 * ## THIS FILE WAS GENERATED VIA SWAGGER-TYPESCRIPT-API        ##
 * ##                                                           ##
 * ## AUTHOR: acacode                                           ##
 * ## SOURCE: https://github.com/acacode/swagger-typescript-api ##
 * ---------------------------------------------------------------
 */

export interface WorkTypeCreateRequestDTO {
  /**
   * @minLength 0
   * @maxLength 50
   */
  name: string;
}

export interface TechnologyCreateRequestDTO {
  /**
   * @minLength 0
   * @maxLength 50
   */
  name: string;
}

export interface CreatePaymentRequestDTO {
  /** @format int64 */
  companyId: number;
  /** @format int64 */
  offerId: number;
  stripeSubscriptionDuration: "ONE_WEEK" | "ONE_MONTH" | "SIX_MONTHS";
}

export interface LocalizationCreateRequestDTO {
  street?: string;
  /** @format int64 */
  buildingNumber?: number;
  city?: string;
}

export interface OfferCreateRequestDTO {
  /**
   * @minLength 0
   * @maxLength 50
   */
  title: string;
  /** @format int64 */
  companyId?: number;
  /** @format int32 */
  salaryMin?: number;
  /** @format int32 */
  salaryMax?: number;
  description: string;
  /** @uniqueItems true */
  technologies?: number[];
  /** @uniqueItems true */
  experiences?: number[];
  /** @uniqueItems true */
  employmentType?: number[];
  /** @uniqueItems true */
  workTypes?: number[];
  requestDTO?: LocalizationCreateRequestDTO;
}

export interface ExperienceCreateRequestDTO {
  /**
   * @minLength 0
   * @maxLength 50
   */
  name: string;
}

export interface EmploymentTypeCreateRequestDTO {
  /**
   * @minLength 0
   * @maxLength 50
   */
  name: string;
}

export interface CreateCompanyRequestDTO {
  /**
   * @minLength 2
   * @maxLength 100
   */
  name: string;
  /** @pattern \d{10} */
  nip?: string;
  email: string;
  /**
   * @minLength 8
   * @maxLength 30
   * @pattern ^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,30}$
   */
  password: string;
}

export interface CreateCandidateRequestDTO {
  /**
   * @minLength 1
   * @maxLength 50
   * @pattern ^[a-zA-Zà-žÀ-Ž'\- ]+$
   */
  firstName: string;
  /**
   * @minLength 1
   * @maxLength 50
   * @pattern ^[a-zA-Zà-žÀ-Ž'\- ]+$
   */
  lastName: string;
  email: string;
  /**
   * @minLength 8
   * @maxLength 30
   * @pattern ^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,30}$
   */
  password: string;
}

export interface RefreshTokenDTO {
  refreshToken?: string;
}

export interface GenerateTokensDTO {
  accessToken?: string;
  refreshToken?: string;
}

export interface CreateApplicationRequestDTO {
  /** @format int64 */
  candidateId?: number;
  /** @format int64 */
  offerId: number;
  pdf?: string[];
}

export interface CreateAdminRequestDTO {
  /**
   * @minLength 3
   * @maxLength 50
   */
  nickname: string;
  email: string;
  /**
   * @minLength 8
   * @maxLength 30
   * @pattern ^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,30}$
   */
  password: string;
}

export interface WorkTypeResponseDTO {
  /** @format int64 */
  id?: number;
  name?: string;
}

export interface Authority {
  /** @format int64 */
  id?: number;
  name?: string;
  /** @uniqueItems true */
  userAuthority?: UserAuthority[];
}

export interface Company {
  /** @format int64 */
  id?: number;
  /** @format date-time */
  createDate?: string;
  isDeleted?: boolean;
  email?: string;
  password?: string;
  githubLink?: string;
  linkedinLink?: string;
  isCompleted?: boolean;
  name?: string;
  /** @pattern ^\d{10}$ */
  nip?: string;
  /**
   * @format int64
   * @min 1
   */
  companySize?: number;
  /** @uniqueItems true */
  payments?: Payment[];
  /** @uniqueItems true */
  offers?: Offer[];
}

export interface EmploymentType {
  /** @format int64 */
  id?: number;
  name?: string;
  /** @uniqueItems true */
  offerEmploymentTypes?: OfferEmploymentType[];
}

export interface Experience {
  /** @format int64 */
  id?: number;
  name?: string;
  /** @uniqueItems true */
  offerExperiences?: OfferExperience[];
}

export interface Localization {
  /** @format int64 */
  id?: number;
  name?: string;
  /** @format double */
  lat?: number;
  /** @format double */
  lng?: number;
  /** @uniqueItems true */
  offers?: Offer[];
}

export interface Offer {
  /** @format int64 */
  id?: number;
  title?: string;
  /** @format date-time */
  expiryDate?: string;
  /**
   * @format int32
   * @min 0
   */
  salaryMin?: number;
  /**
   * @format int32
   * @min 0
   */
  salaryMax?: number;
  description?: string;
  company?: Company;
  localization?: Localization;
  /** @format date-time */
  createdAt?: string;
  /** @uniqueItems true */
  offerTechnologies?: OfferTechnology[];
  /** @uniqueItems true */
  offerExperiences?: OfferExperience[];
  /** @uniqueItems true */
  offerEmploymentTypes?: OfferEmploymentType[];
  /** @uniqueItems true */
  offerWorkTypes?: OfferWorkType[];
  /** @uniqueItems true */
  payments?: Payment[];
  /** @uniqueItems true */
  userFavouriteOffers?: UserFavouriteOffer[];
  paid?: boolean;
}

export interface OfferEmploymentType {
  /** @format int64 */
  id?: number;
  offer?: Offer;
  employmentType?: EmploymentType;
}

export interface OfferExperience {
  /** @format int64 */
  id?: number;
  offer?: Offer;
  experience?: Experience;
}

export interface OfferTechnology {
  /** @format int64 */
  id?: number;
  offer?: Offer;
  technology?: Technology;
}

export interface OfferWorkType {
  /** @format int64 */
  id?: number;
  offer?: Offer;
  workType?: WorkType;
}

export interface Payment {
  /** @format int64 */
  id?: number;
  sessionId?: string;
  amount?: number;
  /** @format date-time */
  purchaseDate?: string;
  status?: "PENDING" | "CANCELED" | "PAID";
  offer?: Offer;
  company?: Company;
  paymentStripePrice?: PaymentStripePrice;
}

export interface PaymentStripePrice {
  stripeSubscriptionDuration?: "ONE_WEEK" | "ONE_MONTH" | "SIX_MONTHS";
  stripePriceId?: string;
  /** @uniqueItems true */
  payments?: Payment[];
}

export interface Technology {
  /** @format int64 */
  id?: number;
  name?: string;
  /** @uniqueItems true */
  offerTechnologies?: OfferTechnology[];
}

export interface User {
  /** @format int64 */
  id?: number;
  /** @format date-time */
  createDate?: string;
  isDeleted?: boolean;
  email?: string;
  password?: string;
  githubLink?: string;
  linkedinLink?: string;
  isCompleted?: boolean;
  /** @uniqueItems true */
  userAuthority?: UserAuthority[];
  /** @uniqueItems true */
  userFavouriteOffers?: UserFavouriteOffer[];
}

export interface UserAuthority {
  /** @format int64 */
  id?: number;
  user?: User;
  authority?: Authority;
}

export interface UserFavouriteOffer {
  /** @format int64 */
  id?: number;
  user?: User;
  offer?: Offer;
}

export interface WorkType {
  /** @format int64 */
  id?: number;
  name?: string;
  /** @uniqueItems true */
  offerWorkTypes?: OfferWorkType[];
}

export interface OfferResponseDTO {
  /** @format int64 */
  id?: number;
  title?: string;
  /** @format date-time */
  expiryDate?: string;
  /** @format int32 */
  salaryMin?: number;
  /** @format int32 */
  salaryMax?: number;
  description?: string;
}

export interface TechnologyResponseDTO {
  /** @format int64 */
  id?: number;
  name?: string;
}

export interface ArticleDTO {
  title?: string;
  description?: string;
  url?: string;
  publishedAt?: string;
}

export interface NewsResponseDTO {
  status?: string;
  /** @format int32 */
  totalResults?: number;
  articles?: ArticleDTO[];
}

export interface CoordinatesDTO {
  /** @format double */
  lat?: number;
  /** @format double */
  lng?: number;
}

export interface ExperienceResponseDTO {
  /** @format int64 */
  id?: number;
  name?: string;
}

export interface EmploymentTypeResponseDTO {
  /** @format int64 */
  id?: number;
  name?: string;
}

export interface CompanyResponseDTO {
  name?: string;
  nip?: string;
  email?: string;
  linkedinLink?: string;
}

export interface CompanyResponseOfferStatsDTO {
  companyName?: string;
  /** @format int64 */
  offerCount?: number;
  /** @format double */
  averageMaxSalary?: number;
}

export interface CandidateResponseDTO {
  firstName?: string;
  lastName?: string;
  /** @format int32 */
  experienceYears?: number;
  email?: string;
  githubLink?: string;
  linkedinLink?: string;
}

export interface AdminResponseDTO {
  /** @format int64 */
  id?: number;
  nickname?: string;
}

export type QueryParamsType = Record<string | number, any>;
export type ResponseFormat = keyof Omit<Body, "body" | "bodyUsed">;

export interface FullRequestParams extends Omit<RequestInit, "body"> {
  /** set parameter to `true` for call `securityWorker` for this request */
  secure?: boolean;
  /** request path */
  path: string;
  /** content type of request body */
  type?: ContentType;
  /** query params */
  query?: QueryParamsType;
  /** format of response (i.e. response.json() -> format: "json") */
  format?: ResponseFormat;
  /** request body */
  body?: unknown;
  /** base url */
  baseUrl?: string;
  /** request cancellation token */
  cancelToken?: CancelToken;
}

export type RequestParams = Omit<FullRequestParams, "body" | "method" | "query" | "path">;

export interface ApiConfig<SecurityDataType = unknown> {
  baseUrl?: string;
  baseApiParams?: Omit<RequestParams, "baseUrl" | "cancelToken" | "signal">;
  securityWorker?: (securityData: SecurityDataType | null) => Promise<RequestParams | void> | RequestParams | void;
  customFetch?: typeof fetch;
}

export interface HttpResponse<D extends unknown, E extends unknown = unknown> extends Response {
  data: D;
  error: E;
}

type CancelToken = Symbol | string | number;

export enum ContentType {
  Json = "application/json",
  FormData = "multipart/form-data",
  UrlEncoded = "application/x-www-form-urlencoded",
  Text = "text/plain",
}

export class HttpClient<SecurityDataType = unknown> {
  public baseUrl: string = "http://localhost:8080";
  private securityData: SecurityDataType | null = null;
  private securityWorker?: ApiConfig<SecurityDataType>["securityWorker"];
  private abortControllers = new Map<CancelToken, AbortController>();
  private customFetch = (...fetchParams: Parameters<typeof fetch>) => fetch(...fetchParams);

  private baseApiParams: RequestParams = {
    credentials: "same-origin",
    headers: {},
    redirect: "follow",
    referrerPolicy: "no-referrer",
  };

  constructor(apiConfig: ApiConfig<SecurityDataType> = {}) {
    Object.assign(this, apiConfig);
  }

  public setSecurityData = (data: SecurityDataType | null) => {
    this.securityData = data;
  };

  protected encodeQueryParam(key: string, value: any) {
    const encodedKey = encodeURIComponent(key);
    return `${encodedKey}=${encodeURIComponent(typeof value === "number" ? value : `${value}`)}`;
  }

  protected addQueryParam(query: QueryParamsType, key: string) {
    return this.encodeQueryParam(key, query[key]);
  }

  protected addArrayQueryParam(query: QueryParamsType, key: string) {
    const value = query[key];
    return value.map((v: any) => this.encodeQueryParam(key, v)).join("&");
  }

  protected toQueryString(rawQuery?: QueryParamsType): string {
    const query = rawQuery || {};
    const keys = Object.keys(query).filter((key) => "undefined" !== typeof query[key]);
    return keys
      .map((key) => (Array.isArray(query[key]) ? this.addArrayQueryParam(query, key) : this.addQueryParam(query, key)))
      .join("&");
  }

  protected addQueryParams(rawQuery?: QueryParamsType): string {
    const queryString = this.toQueryString(rawQuery);
    return queryString ? `?${queryString}` : "";
  }

  private contentFormatters: Record<ContentType, (input: any) => any> = {
    [ContentType.Json]: (input: any) =>
      input !== null && (typeof input === "object" || typeof input === "string") ? JSON.stringify(input) : input,
    [ContentType.Text]: (input: any) => (input !== null && typeof input !== "string" ? JSON.stringify(input) : input),
    [ContentType.FormData]: (input: any) =>
      Object.keys(input || {}).reduce((formData, key) => {
        const property = input[key];
        formData.append(
          key,
          property instanceof Blob
            ? property
            : typeof property === "object" && property !== null
              ? JSON.stringify(property)
              : `${property}`,
        );
        return formData;
      }, new FormData()),
    [ContentType.UrlEncoded]: (input: any) => this.toQueryString(input),
  };

  protected mergeRequestParams(params1: RequestParams, params2?: RequestParams): RequestParams {
    return {
      ...this.baseApiParams,
      ...params1,
      ...(params2 || {}),
      headers: {
        ...(this.baseApiParams.headers || {}),
        ...(params1.headers || {}),
        ...((params2 && params2.headers) || {}),
      },
    };
  }

  protected createAbortSignal = (cancelToken: CancelToken): AbortSignal | undefined => {
    if (this.abortControllers.has(cancelToken)) {
      const abortController = this.abortControllers.get(cancelToken);
      if (abortController) {
        return abortController.signal;
      }
      return void 0;
    }

    const abortController = new AbortController();
    this.abortControllers.set(cancelToken, abortController);
    return abortController.signal;
  };

  public abortRequest = (cancelToken: CancelToken) => {
    const abortController = this.abortControllers.get(cancelToken);

    if (abortController) {
      abortController.abort();
      this.abortControllers.delete(cancelToken);
    }
  };

  public request = async <T = any, E = any>({
    body,
    secure,
    path,
    type,
    query,
    format,
    baseUrl,
    cancelToken,
    ...params
  }: FullRequestParams): Promise<HttpResponse<T, E>> => {
    const secureParams =
      ((typeof secure === "boolean" ? secure : this.baseApiParams.secure) &&
        this.securityWorker &&
        (await this.securityWorker(this.securityData))) ||
      {};
    const requestParams = this.mergeRequestParams(params, secureParams);
    const queryString = query && this.toQueryString(query);
    const payloadFormatter = this.contentFormatters[type || ContentType.Json];
    const responseFormat = format || requestParams.format;

    return this.customFetch(`${baseUrl || this.baseUrl || ""}${path}${queryString ? `?${queryString}` : ""}`, {
      ...requestParams,
      headers: {
        ...(requestParams.headers || {}),
        ...(type && type !== ContentType.FormData ? { "Content-Type": type } : {}),
      },
      signal: (cancelToken ? this.createAbortSignal(cancelToken) : requestParams.signal) || null,
      body: typeof body === "undefined" || body === null ? null : payloadFormatter(body),
    }).then(async (response) => {
      const r = response.clone() as HttpResponse<T, E>;
      r.data = null as unknown as T;
      r.error = null as unknown as E;

      const data = !responseFormat
        ? r
        : await response[responseFormat]()
            .then((data) => {
              if (r.ok) {
                r.data = data;
              } else {
                r.error = data;
              }
              return r;
            })
            .catch((e) => {
              r.error = e;
              return r;
            });

      if (cancelToken) {
        this.abortControllers.delete(cancelToken);
      }

      if (!response.ok) throw data;
      return data;
    });
  };
}

/**
 * @title OpenAPI definition
 * @version v0
 * @baseUrl http://localhost:8080
 */
export class Api<SecurityDataType extends unknown> extends HttpClient<SecurityDataType> {
  api = {
    /**
     * No description
     *
     * @tags work-type-controller
     * @name GetAllWorkTypes
     * @request GET:/api/work-types
     */
    getAllWorkTypes: (params: RequestParams = {}) =>
      this.request<WorkTypeResponseDTO[], any>({
        path: `/api/work-types`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags work-type-controller
     * @name AddWorkType
     * @request POST:/api/work-types
     */
    addWorkType: (data: WorkTypeCreateRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/work-types`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags stripe-webhook-controller
     * @name HandleWebhook
     * @request POST:/api/webhook
     */
    handleWebhook: (data: string, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/webhook`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name GetFavouriteOffers
     * @request GET:/api/users/{userId}/favourite-offers
     */
    getFavouriteOffers: (userId: number, params: RequestParams = {}) =>
      this.request<OfferResponseDTO[], any>({
        path: `/api/users/${userId}/favourite-offers`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name AddFavouriteOffer
     * @request POST:/api/users/{userId}/favourite-offers
     */
    addFavouriteOffer: (
      userId: number,
      query: {
        /** @format int64 */
        offerId: number;
      },
      params: RequestParams = {},
    ) =>
      this.request<string, any>({
        path: `/api/users/${userId}/favourite-offers`,
        method: "POST",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags technology-controller
     * @name GetAllTechnologies
     * @request GET:/api/technologies
     */
    getAllTechnologies: (params: RequestParams = {}) =>
      this.request<TechnologyResponseDTO[], any>({
        path: `/api/technologies`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags technology-controller
     * @name AddTechnology
     * @request POST:/api/technologies
     */
    addTechnology: (data: TechnologyCreateRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/technologies`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags payment-controller
     * @name CreateCheckoutSession
     * @request POST:/api/payments/create-session
     */
    createCheckoutSession: (data: CreatePaymentRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/payments/create-session`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags offer-controller
     * @name GetOffers
     * @request GET:/api/offers
     */
    getOffers: (
      query?: {
        orderBy?: string;
        sortBy?: string;
      },
      params: RequestParams = {},
    ) =>
      this.request<OfferResponseDTO[], any>({
        path: `/api/offers`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags offer-controller
     * @name AddOffer
     * @request POST:/api/offers
     */
    addOffer: (data: OfferCreateRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/offers`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags experience-controller
     * @name GetAllExperiences
     * @request GET:/api/experiences
     */
    getAllExperiences: (params: RequestParams = {}) =>
      this.request<ExperienceResponseDTO[], any>({
        path: `/api/experiences`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags experience-controller
     * @name AddExperience
     * @request POST:/api/experiences
     */
    addExperience: (data: ExperienceCreateRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/experiences`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags employment-type-controller
     * @name GetAllEmploymentTypes
     * @request GET:/api/employment-type
     */
    getAllEmploymentTypes: (params: RequestParams = {}) =>
      this.request<EmploymentTypeResponseDTO[], any>({
        path: `/api/employment-type`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags employment-type-controller
     * @name AddEmploymentType
     * @request POST:/api/employment-type
     */
    addEmploymentType: (data: EmploymentTypeCreateRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/employment-type`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags company-controller
     * @name GetAllCompanies
     * @request GET:/api/companies
     */
    getAllCompanies: (params: RequestParams = {}) =>
      this.request<CompanyResponseDTO[], any>({
        path: `/api/companies`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags company-controller
     * @name CreateCompany
     * @request POST:/api/companies
     */
    createCompany: (data: CreateCompanyRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/companies`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags candidate-controller
     * @name GetAllCandidates
     * @request GET:/api/candidates
     */
    getAllCandidates: (params: RequestParams = {}) =>
      this.request<CandidateResponseDTO[], any>({
        path: `/api/candidates`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags candidate-controller
     * @name CreateCandidate
     * @request POST:/api/candidates
     */
    createCandidate: (data: CreateCandidateRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/candidates`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags auth-controller
     * @name Refresh
     * @request POST:/api/auth/refresh
     */
    refresh: (data: RefreshTokenDTO, params: RequestParams = {}) =>
      this.request<GenerateTokensDTO, any>({
        path: `/api/auth/refresh`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags auth-controller
     * @name Logout
     * @request POST:/api/auth/logout
     */
    logout: (data: RefreshTokenDTO, params: RequestParams = {}) =>
      this.request<void, any>({
        path: `/api/auth/logout`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags auth-controller
     * @name Login
     * @request POST:/api/auth/login
     */
    login: (params: RequestParams = {}) =>
      this.request<GenerateTokensDTO, any>({
        path: `/api/auth/login`,
        method: "POST",
        ...params,
      }),

    /**
     * No description
     *
     * @tags application-controller
     * @name CreateApplication
     * @request POST:/api/applications
     */
    createApplication: (data: CreateApplicationRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/applications`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags admin-controller
     * @name GetAllAdmins
     * @request GET:/api/admins
     */
    getAllAdmins: (params: RequestParams = {}) =>
      this.request<AdminResponseDTO[], any>({
        path: `/api/admins`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags admin-controller
     * @name CreateAdmin
     * @request POST:/api/admins
     */
    createAdmin: (data: CreateAdminRequestDTO, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/admins`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags work-type-controller
     * @name GetWorkType
     * @request GET:/api/work-types/{workTypeId}
     */
    getWorkType: (workTypeId: number, params: RequestParams = {}) =>
      this.request<WorkTypeResponseDTO, any>({
        path: `/api/work-types/${workTypeId}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags work-type-controller
     * @name DeleteWorkType
     * @request DELETE:/api/work-types/{workTypeId}
     */
    deleteWorkType: (workTypeId: number, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/work-types/${workTypeId}`,
        method: "DELETE",
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name GetUsers
     * @request GET:/api/users
     */
    getUsers: (params: RequestParams = {}) =>
      this.request<User[], any>({
        path: `/api/users`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name GetUserById
     * @request GET:/api/users/{id}
     */
    getUserById: (id: number, params: RequestParams = {}) =>
      this.request<User, any>({
        path: `/api/users/${id}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name DeleteUserById
     * @request DELETE:/api/users/{id}
     */
    deleteUserById: (id: number, params: RequestParams = {}) =>
      this.request<User, any>({
        path: `/api/users/${id}`,
        method: "DELETE",
        ...params,
      }),

    /**
     * No description
     *
     * @tags technology-controller
     * @name GetTechnology
     * @request GET:/api/technologies/{technologyId}
     */
    getTechnology: (technologyId: number, params: RequestParams = {}) =>
      this.request<TechnologyResponseDTO, any>({
        path: `/api/technologies/${technologyId}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags technology-controller
     * @name DeleteTechnology
     * @request DELETE:/api/technologies/{technologyId}
     */
    deleteTechnology: (technologyId: number, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/technologies/${technologyId}`,
        method: "DELETE",
        ...params,
      }),

    /**
     * No description
     *
     * @tags pdf-controller
     * @name ViewPdf
     * @request GET:/api/pdf/view/{pdfId}
     */
    viewPdf: (pdfId: number, params: RequestParams = {}) =>
      this.request<File, any>({
        path: `/api/pdf/view/${pdfId}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags offer-controller
     * @name GetOffer
     * @request GET:/api/offers/{offerId}
     */
    getOffer: (offerId: number, params: RequestParams = {}) =>
      this.request<OfferResponseDTO, any>({
        path: `/api/offers/${offerId}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags offer-controller
     * @name DeleteOffer
     * @request DELETE:/api/offers/{offerId}
     */
    deleteOffer: (offerId: number, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/offers/${offerId}`,
        method: "DELETE",
        ...params,
      }),

    /**
     * No description
     *
     * @tags offer-controller
     * @name GetOffersByLocalization
     * @request GET:/api/offers/filter
     */
    getOffersByLocalization: (
      query?: {
        localization?: string;
      },
      params: RequestParams = {},
    ) =>
      this.request<Offer[], any>({
        path: `/api/offers/filter`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags news-controller
     * @name GetNews
     * @request GET:/api/news/api/news
     */
    getNews: (
      query: {
        query: string;
      },
      params: RequestParams = {},
    ) =>
      this.request<NewsResponseDTO, any>({
        path: `/api/news/api/news`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags geocoding-controller
     * @name GetCoordinates
     * @request GET:/api/geocoding
     */
    getCoordinates: (
      query: {
        address: string;
      },
      params: RequestParams = {},
    ) =>
      this.request<CoordinatesDTO, any>({
        path: `/api/geocoding`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags experience-controller
     * @name GetExperience
     * @request GET:/api/experiences/{experienceId}
     */
    getExperience: (experienceId: number, params: RequestParams = {}) =>
      this.request<ExperienceResponseDTO, any>({
        path: `/api/experiences/${experienceId}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags experience-controller
     * @name DeleteExperience
     * @request DELETE:/api/experiences/{experienceId}
     */
    deleteExperience: (experienceId: number, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/experiences/${experienceId}`,
        method: "DELETE",
        ...params,
      }),

    /**
     * No description
     *
     * @tags employment-type-controller
     * @name GetEmploymentType
     * @request GET:/api/employment-type/{employmentTypeId}
     */
    getEmploymentType: (employmentTypeId: number, params: RequestParams = {}) =>
      this.request<EmploymentTypeResponseDTO, any>({
        path: `/api/employment-type/${employmentTypeId}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags employment-type-controller
     * @name DeleteEmploymentType
     * @request DELETE:/api/employment-type/{employmentTypeId}
     */
    deleteEmploymentType: (employmentTypeId: number, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/employment-type/${employmentTypeId}`,
        method: "DELETE",
        ...params,
      }),

    /**
     * No description
     *
     * @tags company-controller
     * @name GetCompanyById
     * @request GET:/api/companies/{id}
     */
    getCompanyById: (id: number, params: RequestParams = {}) =>
      this.request<CompanyResponseDTO, any>({
        path: `/api/companies/${id}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags company-controller
     * @name GetCompaniesSortedBy
     * @request GET:/api/companies/top-companies
     */
    getCompaniesSortedBy: (
      query?: {
        /** @default "average" */
        sortBy?: string;
        /**
         * @format int32
         * @default 0
         */
        page?: number;
        /**
         * @format int32
         * @default 5
         */
        size?: number;
      },
      params: RequestParams = {},
    ) =>
      this.request<CompanyResponseOfferStatsDTO[], any>({
        path: `/api/companies/top-companies`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags candidate-controller
     * @name GetCandidateById
     * @request GET:/api/candidates/{id}
     */
    getCandidateById: (id: number, params: RequestParams = {}) =>
      this.request<CandidateResponseDTO, any>({
        path: `/api/candidates/${id}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags authority-controller
     * @name GetAuthorityByName
     * @request GET:/api/authorities
     */
    getAuthorityByName: (
      query: {
        name: string;
      },
      params: RequestParams = {},
    ) =>
      this.request<Authority, any>({
        path: `/api/authorities`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags authority-controller
     * @name GetAllAuthorities
     * @request GET:/api/authorities/all
     */
    getAllAuthorities: (params: RequestParams = {}) =>
      this.request<Authority[], any>({
        path: `/api/authorities/all`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name DeleteFavouriteOffer
     * @request DELETE:/api/users/{userId}/favourite-offers/{offerId}
     */
    deleteFavouriteOffer: (userId: number, offerId: number, params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/api/users/${userId}/favourite-offers/${offerId}`,
        method: "DELETE",
        ...params,
      }),
  };
}
