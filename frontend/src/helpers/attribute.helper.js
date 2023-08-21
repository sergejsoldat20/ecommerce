export const createListOfFilters = (attributes, filtersData) => {
  let filtersList = [];

  for (const attributeName in filtersData) {
    const attributeType = attributes.find(
      (item) => item.name === attributeName
    )?.type;

    if (filtersData[attributeName] === "") {
      continue;
    }

    if (attributeType === "Integer" || attributeType === "Double") {
      const valueFrom = filtersData[attributeName].split("-")[0];
      const valueTo = filtersData[attributeName].split("-")[1];

      const singleFilter = {
        valueFrom: valueFrom,
        valueTo: valueTo,
        attributeName: attributeName,
      };
      filtersList.push(singleFilter);
    } else {
      const singleFilter = {
        valueFrom: filtersData[attributeName],
        valueTo: null,
        attributeName: attributeName,
      };
      filtersList.push(singleFilter);
    }
  }
  return filtersList;
};

// eslint-disable-next-line import/no-anonymous-default-export
export default {
  createListOfFilters,
};
