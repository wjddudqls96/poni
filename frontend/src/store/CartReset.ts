import { useResetRecoilState } from 'recoil';
import { blankOption, blankSelect, cartIds, explainSelect, traceOption, traceSelect } from './Cart';

function useResetAllAtoms() {
  const resetTraceOption = useResetRecoilState(traceOption);
  const resetBlankOption = useResetRecoilState(blankOption);
  const resetTraceSelect = useResetRecoilState(traceSelect);
  const resetExplainSelect = useResetRecoilState(explainSelect);
  const resetBlankSelect = useResetRecoilState(blankSelect);
  const resetCartIds = useResetRecoilState(cartIds);

  const resetAll = () => {
    resetTraceOption();
    resetBlankOption();
    resetTraceSelect();
    resetExplainSelect();
    resetBlankSelect();
    resetCartIds();
  };

  return resetAll;
}

export default useResetAllAtoms;
