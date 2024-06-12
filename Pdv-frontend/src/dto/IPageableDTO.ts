import { SortDirection } from "./enumSortDirection";

export interface IPageableDTO {
    page?: number;
    size?: number;
    sort?: string;
    direction?: SortDirection;

}